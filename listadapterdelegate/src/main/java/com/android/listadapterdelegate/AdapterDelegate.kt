package com.android.listadapterdelegate

import android.view.ViewGroup

interface AdapterDelegate<T : ListItem> {
    fun onCreateViewHolder(parent: ViewGroup): ListViewHolder<T>
    fun onBindViewHolder(holder: ListViewHolder<T>, dataItem: T)
    fun isViewForDataType(dataItem: ListItem): Boolean
}