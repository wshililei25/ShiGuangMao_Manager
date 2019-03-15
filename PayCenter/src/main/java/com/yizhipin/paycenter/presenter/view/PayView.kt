package com.yizhipin.shop.presenter.view

import com.yizhipin.base.mvp.view.BaseView

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface PayView : BaseView {
    fun onRechargeSuccess(result: String)
}