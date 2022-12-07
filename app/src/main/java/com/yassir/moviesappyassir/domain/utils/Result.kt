package com.yassir.moviesappyassir.domain.utils

sealed class Result<out T> {

    class Success<out T>(val data: T) : Result<T>()

    class Error(val throwable: Throwable?) : Result<Nothing>()

}

fun <T> Result<T>.fold(
    onSuccess: (T) -> Unit,
    onError: (Throwable?) -> Unit,
) {
    when (this) {
        is Result.Success -> {
            onSuccess(data)
        }
        is Result.Error -> {
            onError(throwable)
        }
    }
}