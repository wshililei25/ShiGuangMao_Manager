package com.qi.management.presenter.view

import com.yizhipin.base.mvp.view.BaseView
import com.yizhipin.usercenter.data.response.ManagerOrderDetails

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface ManagerOrderDetailsView : BaseView {
    fun onGetManagerOrderDetailsSuccess(result: ManagerOrderDetails)
   /* fun onOrderSuccess(result: OrderDetails)
    fun onOrderDetailsSuccess(result: OrderDetails)
    fun onFollowSuccess(result: Boolean)
    fun onGetEvaluateSuccess(result: MutableList<Evaluate>)
    fun onGetBasicServicesSuccess(result: MutableList<BasicServices>)*/
}