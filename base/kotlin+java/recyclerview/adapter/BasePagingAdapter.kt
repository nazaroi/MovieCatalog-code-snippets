package com.nazaroi.base.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BasePagingAdapter<T : Any, VB : ViewBinding, VH : BaseViewHolder<T, VB>>(
    diffCallback: DiffUtil.ItemCallback<T>, private val onItemClick: ((T) -> Unit)? = null
    ) : PagingDataAdapter<T, VH>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = inflateBinding(layoutInflater, parent, viewType)
        val viewHolder = createViewHolder(binding, viewType)
        binding.root.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                getItem(position)?.let { item -> onItemClick?.invoke(item) }
            }
        }
        return viewHolder
    }

    abstract fun createViewHolder(binding: VB, viewType: Int): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    protected abstract fun inflateBinding(
        layoutInflater: LayoutInflater, parent: ViewGroup, viewType: Int
    ): VB
}