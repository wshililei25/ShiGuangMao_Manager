package com.yizhipin.usercenter.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by ${XiLei} on 2018/10/4.
 */
@Parcelize
class Banner(var id: Int,
             var imgurl: String,
             var url: String,
             var type: String):Parcelable