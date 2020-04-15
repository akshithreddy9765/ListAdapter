package com.android.listadapterdelegate

import android.view.ViewGroup
import java.util.*


open class DelegateManager<T : ListItem>() {
    private val delegates = ArrayList<ListAdapterDelegate<T>>()

    fun addDelegates(delegate: ListAdapterDelegate<T>): DelegateManager<T> {
        delegates.add(delegate)
        return this
    }

    fun addDelegates(position: Int, delegate: ListAdapterDelegate<T>): DelegateManager<T> {
        delegates.add(position, delegate)
        return this
    }

    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder<T> =
        delegates[viewType].onCreateViewHolder(parent)

    fun onBindViewHolder(viewHolder: ListViewHolder<T>, data: T) =
        delegates[viewHolder.itemViewType].onBindViewHolder(viewHolder, data)


    fun getViewType(data: ListItem): Int {
        delegates.forEachIndexed { index, listAdapterDelegate ->
            if (listAdapterDelegate.isViewForDataType(data)) {
                return index
            }
        }
        throw IllegalArgumentException("No AdapterDelegate found for data: $data")
    }
}