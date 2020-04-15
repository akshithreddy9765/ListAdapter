package com.android.listadapterdelegate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class ListViewHolder<T : ListItem>(parent: ViewGroup, layoutId: Int) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId, parent)) {
    abstract fun onBindData(item: T)
}