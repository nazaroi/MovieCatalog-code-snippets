package com.nazaroi.feature_search

import androidx.paging.PagingData
import com.nazaroi.base.fault.Fault
import com.nazaroi.base.mvi.MviState
import com.nazaroi.domain.model.movie.ShortMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchState(
    val loading: Boolean = false,
    val streamingMoviesPagingData: Flow<PagingData<ShortMovie>> = emptyFlow(),
    val fault: Fault? = null
) : MviState