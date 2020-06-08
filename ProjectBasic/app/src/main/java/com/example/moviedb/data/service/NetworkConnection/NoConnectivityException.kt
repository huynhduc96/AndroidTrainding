package com.example.moviedb.data.service.NetworkConnection

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No Internet Connection"
}
