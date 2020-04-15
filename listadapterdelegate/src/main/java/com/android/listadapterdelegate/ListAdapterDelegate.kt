package com.android.listadapterdelegate

abstract class ListAdapterDelegate<T : ListItem> : AdapterDelegate<T> {
    override fun onBindViewHolder(holder: ListViewHolder<T>, dataItem: T) {
        holder.onBindData(dataItem)
    }
}