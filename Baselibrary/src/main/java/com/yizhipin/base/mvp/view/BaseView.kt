package com.yizhipin.base.mvp.view

import android.support.annotation.StringRes

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(mes: String)
    fun onDataIsNull(){}//默认实现
}