package com.figuredin.controllers.Controller.other

import java.text.SimpleDateFormat
import java.util.*

fun Date.dateToString(format : String, locale: Locale = Locale.getDefault()) : String{
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}