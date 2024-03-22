package com.nazaroi.base.recyclerview.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<T, VB : ViewBinding>(protected val binding: VB) :
    RecyclerView.ViewHolder(binding.root) {

    open fun bind(item: T) {
        bindItem(item)
    }

    abstract fun bindItem(item: T)
}