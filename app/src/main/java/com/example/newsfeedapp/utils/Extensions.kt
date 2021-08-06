package com.example.newsfeedapp.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val defaultFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private const val dateFormat = "dd/MM/yyyy"

@SuppressLint("SimpleDateFormat")
fun String.timeFormat(
    locale: Locale = Locale.getDefault()
): String {
    val inputFormat = SimpleDateFormat(defaultFormat)
    val outputFormat = SimpleDateFormat(dateFormat)
    return try {
        val date = inputFormat.parse(this)
        outputFormat.format(date!!)
    } catch (e: ParseException) {
        ""
    }
}

fun Context.isNetworkConnected(): Boolean {
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return when {
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }
        else -> {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            nwInfo.isConnected
        }
    }
}