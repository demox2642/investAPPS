package com.example.utils.data_time

import java.text.SimpleDateFormat

sealed class DataTimeFormater(
    val format: String,
) {
    class YearMonthDayShort : DataTimeFormater(format = "yyyy-MM-dd")

    fun toSimpleFormat(): SimpleDateFormat = SimpleDateFormat(this.format)
}
