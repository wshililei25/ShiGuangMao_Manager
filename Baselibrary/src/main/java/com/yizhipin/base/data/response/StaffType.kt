package com.yizhipin.usercenter.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by ${XiLei} on 2018/8/18.
 */
@Parcelize
class StaffType(
        val id: String,
        val code: String,
        val positionName: String,
        val authCode: String
) : Parcelable