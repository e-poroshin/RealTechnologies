package uk.co.real_technologies.statistics

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import uk.co.real_technologies.R
import uk.co.real_technologies.categories.CategoryViewModel
import uk.co.real_technologies.di.App
import uk.co.real_technologies.fragments.OnFragmentActionListener
import uk.co.real_technologies.operations.OperationType
import uk.co.real_technologies.operations.OperationsViewModel
import uk.co.real_technologies.repo.database.Operation
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import java.util.*
import javax.inject.Inject

class StatisticsFragment : Fragment() {

    @Inject
    lateinit var viewModelCategory: CategoryViewModel
    @Inject
    lateinit var viewModelOperation: OperationsViewModel

    private var onFragmentActionListener: OnFragmentActionListener? = null
    private var toolbar: Toolbar? = null
    private var pieChart: PieChart? = null
    private var operations: List<Operation> = ArrayList()
    private var categoryNames: List<String> = ArrayList()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentActionListener) {
            onFragmentActionListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.fragmentSubComponentBuilder().with(this).build().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =
            inflater.inflate(R.layout.fragment_statistics, container, false)
        toolbar = view.findViewById(R.id.my_toolbar)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        pieChart = view.findViewById(R.id.pieChart)
        viewModelOperation.liveData.observe(
            viewLifecycleOwner,
            Observer { operationList ->
                operations = operationList
                viewModelCategory.liveDataCategoryNames.observe(
                    viewLifecycleOwner,
                    Observer { categories ->
                        categoryNames = categories
                        setUpPieChart(operations, categoryNames)
                    })
            })
        return view
    }

    private fun setUpPieChart(
        operationList: List<Operation>,
        categoryNames: List<String>
    ) {
        val pieEntries: MutableList<PieEntry> = ArrayList()
        for (j in categoryNames.indices) {
            var sum = 0.0
            var label: String? = ""
            for (i in operationList.indices) {
                if (operationList[i].operationEntity?.type == OperationType.CONSUMPTION
                ) {
                    if (categoryNames[j] == operationList[i].category?.name) {
                        sum += operationList[i].operationEntity?.sum!!
                        label = operationList[i].category?.name
                    }
                }
            }
            if (sum != 0.0) {
                pieEntries.add(PieEntry(sum.toFloat(), label))
            }
        }
        val dataSet = PieDataSet(pieEntries, "")
        dataSet.setColors(*ChartColors.MATERIAL_COLORS)
        dataSet.valueTextColor = R.color.colorPrimaryDark
        dataSet.valueTextSize = 14f
        val data = PieData(dataSet)
        pieChart!!.data = data
        pieChart!!.animateXY(1000, 1000)
        pieChart!!.invalidate()
    }

    override fun onDestroy() {
        super.onDestroy()
        onFragmentActionListener = null
    }

    companion object {
        fun newInstance(): StatisticsFragment {
            return StatisticsFragment()
        }
    }
}