package uk.co.real_technologies.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.co.real_technologies.R
import uk.co.real_technologies.fragments.FragmentCommunicator
import uk.co.real_technologies.repo.database.CategoryEntity
import java.util.*

class CategoriesAdapter(
    categoryList: List<CategoryEntity>,
    communication: FragmentCommunicator
) : RecyclerView.Adapter<CategoriesAdapter.RecyclerViewHolder>() {

    private var categories: List<CategoryEntity>?
    private val communicator: FragmentCommunicator

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_list_item, parent, false)
        return RecyclerViewHolder(
            view,
            communicator
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerViewHolder,
        position: Int
    ) {
        when (categories!![position].id) {
            1 -> holder.imageViewIcon.setImageResource(R.drawable.group_18)
            2 -> holder.imageViewIcon.setImageResource(R.drawable.group_19)
            3 -> holder.imageViewIcon.setImageResource(R.drawable.group_20)
            4 -> holder.imageViewIcon.setImageResource(R.drawable.group_21)
            5 -> holder.imageViewIcon.setImageResource(R.drawable.group_22)
            6 -> holder.imageViewIcon.setImageResource(R.drawable.group_23)
            7 -> holder.imageViewIcon.setImageResource(R.drawable.group_24)
            8 -> holder.imageViewIcon.setImageResource(R.drawable.group_25)
            9 -> holder.imageViewIcon.setImageResource(R.drawable.group_29)
            else -> holder.imageViewIcon.setImageResource(R.drawable.group_26)
        }
        holder.textViewName.text = categories!![position].name
    }

    override fun getItemCount(): Int {
        return if (categories != null) {
            categories!!.size
        } else 0
    }

    fun setCategories(categoryList: List<CategoryEntity>?) {
        categories = categoryList
        notifyDataSetChanged()
    }

    inner class RecyclerViewHolder(
        itemView: View,
        mCommunicator: FragmentCommunicator
    ) : RecyclerView.ViewHolder(itemView) {

        val imageViewIcon: ImageView = itemView.findViewById(R.id.itemImageView)
        val textViewName: TextView = itemView.findViewById(R.id.itemTextView)
        private val mCommunication: FragmentCommunicator = mCommunicator

        init {
            itemView.setOnClickListener {
                val text = textViewName.text.toString()
                mCommunication.onItemClickListener(text)
            }
        }
    }

    init {
        categories = ArrayList(categoryList)
        communicator = communication
    }
}