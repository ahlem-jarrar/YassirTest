package com.yassir.moviesappyassir.data.exception

class GeneralException : Exception() {
    override fun getLocalizedMessage(): String {
        return "An error occurred."
    }
}