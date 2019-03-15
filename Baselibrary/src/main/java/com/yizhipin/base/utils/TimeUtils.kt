package com.yizhipin.base.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Creator Qi
 * Date 2019/2/17
 */
object TimeUtils {
    const val YMDHMS = "yyyy-MM-dd HH:mm:ss"
    fun toString(date: Date, format: String): String {
        val simpleFormatter = SimpleDateFormat(format, Locale.getDefault())
        return simpleFormatter.format(date)
    }

    fun toDate(string: String, format: String): Date {
        val simpleFormatter = SimpleDateFormat(format, Locale.getDefault())
        return simpleFormatter.parse(string)
    }
}