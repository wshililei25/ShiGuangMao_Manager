package com.qi.management.presenter.view

import com.yizhipin.base.data.response.ManagerOrder
import com.yizhipin.base.mvp.view.BaseView

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface StaffDetailsView : BaseView {
    fun onGetStaffDetailsSuccess(result: MutableList<ManagerOrder>)
}