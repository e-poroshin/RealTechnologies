package uk.co.real_technologies.categories

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uk.co.real_technologies.R
import uk.co.real_technologies.di.App
import uk.co.real_technologies.fragments.AddCategoryDialogFragment
import uk.co.real_technologies.fragments.AddCategoryDialogFragment.EditNameDialogListener
import uk.co.real_technologies.fragments.FragmentCommunicator
import uk.co.real_technologies.fragments.OnMainFragmentsActionListener
import uk.co.real_technologies.repo.database.AccountEntity
import uk.co.real_technologies.repo.database.CategoryEntity
import java.util.*
import javax.inject.Inject

class CategoriesFragment : Fragment(), EditNameDialogListener {

    @Inject
    lateinit var viewModel: CategoryViewModel

    private var onMainFragmentsActionListener: OnMainFragmentsActionListener? = null
    private var toolbar: Toolbar? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: CategoriesAdapter? = null
    private var categories: List<CategoryEntity> = ArrayList()
    private var addCategoryDialogFragment: AddCategoryDialogFragment? = null
    private val communicator = object : FragmentCommunicator {
        override fun onItemClickListener(categoryName: String?) {}
        override fun onItemAccountClickListener(accountEntity: AccountEntity?) {}
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnMainFragmentsActionListener) {
            onMainFragmentsActionListener = context
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
        val view = inflater.inflate(R.layout.fragment_categories, container, false)
        toolbar = view.findViewById(R.id.my_toolbar)
        (activity as AppCompatActivity?)!!.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)
        recyclerView = view.findViewById(R.id.recycler_view_categories)
        return view
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.categories_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_add_category) {
            addCategoryDialogFragment = AddCategoryDialogFragment()
            addCategoryDialogFragment!!.setTargetFragment(this@CategoriesFragment, 1)
            addCategoryDialogFragment!!.show(
                parentFragmentManager,
                addCategoryDialogFragment!!::class.java.name
            )
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFinishEditDialog(inputText: String?) {
        if (inputText != null) viewModel.insert(CategoryEntity(inputText))
        if (onMainFragmentsActionListener != null) {
            onMainFragmentsActionListener!!.onOpenCategoriesFragment()
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CategoriesAdapter(categories, communicator)
        recyclerView!!.layoutManager = LinearLayoutManager(activity)
        recyclerView!!.adapter = adapter
        viewModel.liveDataCategories.observe(
            viewLifecycleOwner,
            Observer { categoryEntities ->
                categories = categoryEntities
                adapter!!.setCategories(categories)
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        onMainFragmentsActionListener = null
    }

    companion object {
        fun newInstance(): CategoriesFragment {
            return CategoriesFragment()
        }
    }
}