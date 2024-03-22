package com.nazaroi.common.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nazaroi.common.databinding.PagingLoadStateFooterBinding

class PagingLoadStateAdapter(
    private val retry: () -> Unit,
) : LoadStateAdapter<PagingLoadStateAdapter.InnerViewHolder>() {

    override fun onBindViewHolder(holder: InnerViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ): InnerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PagingLoadStateFooterBinding.inflate(layoutInflater, parent, false)

        return InnerViewHolder(binding)
    }

    inner class InnerViewHolder(
        private val binding: PagingLoadStateFooterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
            binding.errorLayout.isVisible = loadState is LoadState.Error
        }
    }
}