package com.yizhipin.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Creator Qi
 * Date 2019/1/21
 * 押金
 */
@Parcelize
data class BeanDeposit(
    val available: Double,
    val total: Double
): Parcelable