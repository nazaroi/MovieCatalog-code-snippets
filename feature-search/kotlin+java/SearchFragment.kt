package com.nazaroi.feature_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.nazaroi.base.ktx.streamTextChanges
import com.nazaroi.common.paging.PagingLoadStateAdapter
import com.nazaroi.common.screen.Screen
import com.nazaroi.common.shared.SharedMviFragment
import com.nazaroi.feature_search.adapter.SearchResultAdapter
import com.nazaroi.feature_search.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
@AndroidEntryPoint
class SearchFragment :
    SharedMviFragment<FragmentSearchBinding, SearchViewModel, SearchState, SearchIntent, SearchEffect>() {

    override val viewModel: SearchViewModel by viewModels()

    private lateinit var searchResultAdapter: SearchResultAdapter

    override fun createViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setState(Screen.Search)
    }

    override fun setupViews() {

        binding.root.setOnRefreshListener {
            binding.root.isRefreshing = false
            searchResultAdapter.refresh()
        }

        searchResultAdapter = SearchResultAdapter {
            viewModel.sendIntent(SearchIntent.NavigateToMovieTrailer(it.id))
        }

        searchResultAdapter.addLoadStateListener { loadState ->
            // binding.recyclerView.isVisible = loadState.refresh !is LoadState.Loading

            val isEmptyList =
                loadState.refresh is LoadState.NotLoading && searchResultAdapter.itemCount == 0

            binding.noMoviesFoundPlaceholder.isVisible =
                isEmptyList && binding.editText.text.isNotEmpty()
        }

        binding.recyclerView.adapter =
            searchResultAdapter.withLoadStateFooter(PagingLoadStateAdapter {
                searchResultAdapter.retry()
            })

        searchResultAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collectLatest { state ->
                state.streamingMoviesPagingData.collectLatest { pagingData ->
                    searchResultAdapter.submitData(pagingData)
                }
            }
        }

        binding.editText.streamTextChanges().debounce(500).onEach { query ->
            viewModel.sendIntent(SearchIntent.ChangeQuery(query))
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        binding.editText.doAfterTextChanged {
            binding.clearButton.isVisible = it?.length != 0
        }

        binding.clearButton.setOnClickListener {
            binding.editText.setText("")
        }
    }

    override fun handleEffect(effect: SearchEffect) {
    }

    override fun renderState(state: SearchState) {
    }
}