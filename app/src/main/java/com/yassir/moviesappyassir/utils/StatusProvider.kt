package com.yassir.moviesappyassir.utils

/**
 * Application Status Provider.
 */
interface StatusProvider {

    /**
     * Checks network connectivity status.
     */
    fun isOnline(): Boolean
}
