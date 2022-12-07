package com.yassir.moviesappyassir.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yassir.moviesappyassir.domain.Repository
import com.yassir.moviesappyassir.domain.model.MovieDetailsModel
import com.yassir.moviesappyassir.domain.utils.fold
import com.yassir.moviesappyassir.ui.model.LoadingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _onError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val onError: StateFlow<Boolean> = _onError

    private val _movie: MutableStateFlow<MovieDetailsModel?> = MutableStateFlow(null)
    val movie: StateFlow<MovieDetailsModel?> = _movie

    private val _loadingState: MutableStateFlow<LoadingState> = MutableStateFlow(LoadingState.None)
    val loadingState: StateFlow<LoadingState> = _loadingState


    fun loadMovie(movieId: Int) {
        _loadingState.value = LoadingState.Loading
        _onError.value = false

        queryMovie(movieId)
    }

    private fun queryMovie(movieId: Int) {
        viewModelScope.launch {
            repository.getMovieDetails(movieId).fold(
                onSuccess = {
                    _movie.value = it
                    _loadingState.value = LoadingState.None
                },
                onError = {
                    _onError.value = true
                    _loadingState.value = LoadingState.None

                }
            )
        }
    }

}