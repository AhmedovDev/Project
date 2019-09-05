package ru.diitcenter.optovik.presentation.global.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

class NetworkChecking @Inject constructor(
    val context: Context
) {
    fun hasConnection(): Boolean {
        val cm: ConnectivityManager? = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = cm?.activeNetworkInfo
        return networkInfo?.isConnected ?: false
    }
}
