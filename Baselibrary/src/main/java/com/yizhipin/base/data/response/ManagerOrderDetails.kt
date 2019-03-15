package com.yizhipin.usercenter.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by ${XiLei} on 2018/8/18.
 */
@Parcelize
class ManagerOrderDetails(
        val amount: String,
        val amountCoupon: String,
        val attractions: String,
        val clothes: String,
        val couponAmount: String,
        val couponId: String,
        val createTime: String,
        val earnestCoupon: String,
        val earnestMoney: String,
        val earnestOrdernum: String,
        val earnestPay: Boolean,
        val earnestPayType: String,
        val earnestPaytime: String,
        val groupId: String,
        val id: String,
        val imgurl: String,
        val infos: String,
        val ordernum: String,
        val packages: String,
        val payTime: String,
        val payType: String,
        val status: String,
        val storeId: String,
        val teachers: String,
        val title: String,
        val type: String,
        val uid: String
) : Parcelable