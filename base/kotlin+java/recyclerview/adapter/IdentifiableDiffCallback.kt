package com.nazaroi.base.recyclerview.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class IdentifiableDiffCallback<T : Identifiable> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.uniqueId == newItem.uniqueId
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}

interface Identifiable {
    val uniqueId: Any
}
