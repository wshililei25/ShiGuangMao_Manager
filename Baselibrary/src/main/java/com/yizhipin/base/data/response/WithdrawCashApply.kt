package com.yizhipin.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class WithdrawCashApply(
    val amount: String,
    val auditTime: String,
    val createTime: String,
    val id: String,
    val imgurl: String,
    val nickname: String,
    val payType: String,
    val status: String,
    val storeId: String,
    val teacherType: String,
    val uid: String
) : Parcelable

