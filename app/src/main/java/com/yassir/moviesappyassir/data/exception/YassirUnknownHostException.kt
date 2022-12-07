package com.yassir.moviesappyassir.data.exception

import java.net.UnknownHostException

class YassirUnknownHostException : UnknownHostException() {
    override fun getLocalizedMessage(): String {
        return "There is nothing to display."
    }
}