package com.yizhipin.base.data.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by ${XiLei} on 2018/8/18.
 * 用户实体类
 */
@Parcelize
class UserInfo(var id: String,
               var nickname: String,
               var imgurl: String,
               var mobile: String,
               var password: String,
               var realName: String,
               var idCard: String,
               var cardBefore: String,
               var cardAfter: String,
               var totalAmount: String,
               var freezeAmount: String,
               var amount: String,
               var relatedUser: String,
               /**类型(0个人,1老师,2管理人员)*/
               var type: Int,
               var score: String,
               var credit: String,
               var requestCode: String,
               var registerTime: String,
               var lastLoginTime: String? = null,
               var pid: String,
               var photoAmount: String,
               var extraAmount: String,
               var teacherType: String,
               var shopId: String,
               var level: String = "0",
               var position: String,
               var openid: String,
               var qqid: String,
               var weiboid: String,
               var totalDeposit: String,
               var deposit: String,
               var deviceToken: String,
               var deviceType: String,
               var token: String? = null,
               var authCode: String? = null,
               var work: Boolean,
               var storeName: String? = null,
               var hot: Boolean,
               var redPrompt: String
) : Parcelable

object UserType {
    /**0个人*/
    const val Personal = 0
    /**1老师*/
    const val Teacher = 1
    /**2管理人员*/
    const val Management = 2
}