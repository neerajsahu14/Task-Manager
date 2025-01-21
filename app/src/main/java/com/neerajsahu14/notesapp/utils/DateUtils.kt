package com.neerajsahu14.notesapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateUtils private constructor() { // Private constructor to prevent instantiation

    companion object {

        const val DATE_TIME_FORMAT = "dd/MM/yyyy, h:mm a"
        fun formatDate(milliseconds: Long, format: String): String {
            val date = Date(milliseconds)
            val convertedFormat = SimpleDateFormat(format, Locale.getDefault())
            return convertedFormat.format(date)
        }
    }
}