package com.yizhipin

import java.text.SimpleDateFormat
import java.util.*

/**
 * Creator Qi
 * Date 2018/12/9
 */

class TimeTransformerUtil {
    companion object {
        const val TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
        const val FORMAT_YMD = "yyyy-MM-dd"


        fun getDate(text: String): Date {
            val simpleDateFormat = SimpleDateFormat(TimeTransformerUtil.TIME_FORMAT, Locale.getDefault())
            return simpleDateFormat.parse(text)
        }

        fun getYear(text: String): Int {
            val calendar = Calendar.getInstance()
            calendar.time = getDate(text)
            return calendar.get(Calendar.YEAR)
        }

        fun getMonth(time: String): Int {
            val calendar = Calendar.getInstance()
            calendar.time = getDate(time)
            return calendar.get(Calendar.MONTH)
        }

        fun getDayOfMonth(time: String): Int {
            val calendar = Calendar.getInstance()
            calendar.time = getDate(time)
            return calendar.get(Calendar.DAY_OF_MONTH)
        }

        fun getCurrentYMD(): String {
            val calendar = Calendar.getInstance()
            val time = calendar.time
            val simpleDateFormat = SimpleDateFormat(TimeTransformerUtil.FORMAT_YMD, Locale.getDefault())
            return simpleDateFormat.format(time)
        }

        fun toString(time: Date, format: String): String {
            var simpleDateFormat = SimpleDateFormat(format, Locale.getDefault())
            return simpleDateFormat.format(time)
        }

    }
}
