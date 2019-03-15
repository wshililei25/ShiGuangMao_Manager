package com.yizhipin.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class News(
        val content: String,
        val id: String,
        val imgurl: String? = null,
        val nickname: String? = null,
        val pushTime: String,
        val pushUid: String,
        val storeId: String,
        val title: String,
        val type: String,
        val uid: String
) : Parcelable

