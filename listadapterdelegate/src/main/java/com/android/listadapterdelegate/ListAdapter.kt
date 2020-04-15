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
        position
            .takeIf {
                it in 0..listItems.size
            }?.let {
                listItems.add(it, listItem)
                notifyItemInserted(it)
            }
        throw IllegalArgumentException("Position was $position but array length was only ${listItems.size}")
    }

    fun removeItem(listItem: T) {
        listItems.indexOf(listItem)
            .takeIf { it >= 0 }
            ?.let {
                listItems.removeAt(it)
                notifyItemRemoved(it)
            }
    }

    fun removeItemAt(position: Int) {
        position
            .takeIf {
                it in 0 until listItems.size
            }?.let {
                listItems.removeAt(position)
                notifyItemRemoved(position)
                return
            }
        throw IllegalArgumentException("Item position should be from 0 to ${listItems.size} size")
    }

    fun getItemAt(position: Int): T {
        position
            .takeIf {
                it in 0 until listItems.size
            }
            ?.let { return listItems[it] }
        throw IllegalArgumentException("Item position should be from 0 to ${listItems.size} size")
    }

    fun clear() {
        listItems.clear()
        notifyDataSetChanged()
    }
}