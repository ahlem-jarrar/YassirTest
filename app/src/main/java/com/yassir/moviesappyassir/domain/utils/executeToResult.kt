package com.yassir.moviesappyassir.domain.utils

inline fun <T> executeToResult(block: () -> T): Result<T> {
    return try {
        Result.Success(block())
    } catch (t: Throwable) {
        Result.Error(t)
    }
}