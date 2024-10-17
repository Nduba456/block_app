package com.myapplication.utils

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkCheck {
        companion object {
            fun isNetworkAvailable(context: Context?): Boolean {
                if (context == null) {
                    return false
                }

                val connectivityManager =
                    context.getSystemService(Application.CONNECTIVITY_SERVICE) as ConnectivityManager?

                return connectivityManager?.run {
                    val network = activeNetwork
                    val capabilities = getNetworkCapabilities(network)
                    capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                } ?: false
            }
        }
}