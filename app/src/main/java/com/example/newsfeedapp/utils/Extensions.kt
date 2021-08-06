package com.example.newsfeedapp.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val defaultFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private const val dateFormat = "dd/MM/yyyy"

@SuppressLint("SimpleDateFormat")
fun String.timeFormat(
    locale: Locale = Locale.getDefault()
):String{
    val inputFormat = SimpleDateFormat(defaultFormat)
    val outputFormat = SimpleDateFormat(dateFormat)
    return try {
        val date = inputFormat.parse(this)
        outputFormat.format(date!!)
    } catch (e: ParseException) {
        ""
    }
}