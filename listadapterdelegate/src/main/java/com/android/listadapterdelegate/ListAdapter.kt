package com.android.listadapterdelegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListAdapter<T : ListItem>(private val delegateManager: DelegateManager<T>) :
    RecyclerView.Adapter<ListViewHolder<T>>() {

    private var listItems = arrayListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder<T> =
        delegateManager.onCreateViewHolder(parent, viewType)

    override fun onBindViewHolder(holder: ListViewHolder<T>, position: Int) =
        delegateManager.onBindViewHolder(holder, listItems[position])

    override fun getItemCount(): Int = listItems.size

    override fun getItemViewType(position: Int): Int =
        delegateManager.getViewType(listItems[position])

    fun setItems(listItems: ArrayList<T>) {
        this.listItems = listItems
        notifyDataSetChanged()
    }

    fun addItem(listItem: T) {
        listItems.add(listItem)
        notifyItemInserted(listItems.size - 1)
    }

    fun addItemAtPosition(listItem: T, position: Int) {
        if (position < 0 && position >= listItems.size) {
            throw IllegalArgumentException("Position was $position but array length was only ${listItems.size}")
        }
        listItems.add(position, listItem)
        notifyItemInserted(position)
    }

    fun removeItem(listItem: T) {
        val position: Int = listItems.indexOf(listItem)
        if (position >= 0) {
            listItems.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun removeItemAt(position: Int) {
        if (position >= 0 && position < listItems.size) {
            listItems.removeAt(position)
            notifyItemRemoved(position)
        } else {
            throw IllegalArgumentException("Item position should be from 0 to ${listItems.size} size")
        }
    }

    fun getItemAt(position: Int): T {
        return if (position < listItems.size && position >= 0) {
            listItems[position]
        } else {
            throw IllegalArgumentException("Item position should be from 0 to ${listItems.size} size")
        }
    }

    fun clear() {
        listItems.clear()
        notifyDataSetChanged()
    }
}