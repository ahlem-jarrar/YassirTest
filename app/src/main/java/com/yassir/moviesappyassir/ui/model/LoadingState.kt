package com.yassir.moviesappyassir.ui.model

sealed interface LoadingState {
    object None : LoadingState
    object Loading : LoadingState
}
