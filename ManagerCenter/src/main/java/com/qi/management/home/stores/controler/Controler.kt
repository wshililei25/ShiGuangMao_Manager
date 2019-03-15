package com.qi.management.home.stores.controler

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes

/**
 * Creator Qi
 * Date 2019/1/27
 */
abstract class Controler {
    abstract fun setData(items: MutableList<HomeGridItem>)

    abstract fun getData(): MutableList<HomeGridItem>
}

data class HomeGridItem(
        @StringRes val name: Int,
        @DrawableRes val drawable: Int,
        val path: String
)

