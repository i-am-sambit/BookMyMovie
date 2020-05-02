package com.sambitprakash.bookmymovie.utils.date

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun String.convertToDate(): String {
    return if (this.isEmpty()) {
        this
    } else {
        val currentFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH)
        val date = LocalDate.parse(this, currentFormatter)

        val requiredFormatter = DateTimeFormatter.ofPattern("MMMM d, YYYY", Locale.ENGLISH)
        date.format(requiredFormatter).toString()
    }
}
