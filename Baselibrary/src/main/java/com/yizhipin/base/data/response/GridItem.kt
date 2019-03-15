package com.yizhipin.base.data.response

import android.graphics.drawable.AdaptiveIconDrawable
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by ${XiLei} on 2018/8/18.
 */
@Parcelize
class GridItem(
        val drawable: Int,
        val name: String
) : Parcelable