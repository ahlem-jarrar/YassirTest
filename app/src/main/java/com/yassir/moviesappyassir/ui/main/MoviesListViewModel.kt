package com.yassir.moviesappyassir.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.yassir.moviesappyassir.domain.Repository
import com.yassir.moviesappyassir.domain.model.MoviesFilter
import com.yassir.moviesappyassir.ui.mapper.toViewMovieItem
import com.yassir.moviesappyassir.ui.model.ViewMovieItem
import com.yassir.moviesappyassir.utils.StatusProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(
    private val repository: Repository,
    private val statusProvider: StatusProvider) : ViewModel() {

    private val _selectedMovieId: Channel<Int> = Channel()
    val selectedMovieId: Flow<Int> = _selectedMovieId.receiveAsFlow()

    private val _isOffline: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isOffline: StateFlow<Boolean> = _isOffline

    fun getTrendingMovies(filter: MoviesFilter? = null): Flow<PagingData<ViewMovieItem>> {

        if (!statusProvider.isOnline()) {
            _isOffline.value = true
        }
        return repository.getTrendingMovies(filter)
            .map { pagingData ->
                pagingData.map { movie ->
                    movie.toViewMovieItem {
                        _selectedMovieId.trySend(movie.id)
                    }
                }
            }
            .cachedIn(viewModelScope)
    }

}