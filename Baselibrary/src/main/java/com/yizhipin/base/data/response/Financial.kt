package com.yizhipin.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Financial(

        val begin: String? = null,
        val depositAmout: String,
        val earnest: String,
        val end: String? = null,
        val income: String,
        val orderCount: String,
        val queryTime: String? = null,
        val queryTimeType: String? = null,
        val queryType: String? = null,
        val storeId: String,
        val userCount: String,
        val withdrawAmount: String,
        val withdrawCount: String
) : Parcelable
