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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class MoviesListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _selectedMovieId: Channel<Int> = Channel()
    val selectedMovieId: Flow<Int> = _selectedMovieId.receiveAsFlow()

    fun getTrendingMovies(filter: MoviesFilter? = null): Flow<PagingData<ViewMovieItem>> {
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