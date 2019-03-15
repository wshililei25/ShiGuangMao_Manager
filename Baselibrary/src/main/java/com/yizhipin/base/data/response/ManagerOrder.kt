package com.yizhipin.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ManagerOrder(
        val amount: String? = null,
        val headImgurl: String? = null,
        val id: String,
        val imgurl: String,
        val introduction: String? = null,
        val nickname: String? = null,
        val orderStatusMap: String? = null,
        val orderTime: String? = null,
        val orderType: String,
        val status: String? = null,
        val storeId: String? = null,
        val title: String? = null,
        val uid: String? = null
) : Parcelable

