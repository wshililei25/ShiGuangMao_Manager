package com.qi.management.presenter.view

import com.yizhipin.base.mvp.view.BaseView
import com.yizhipin.usercenter.data.response.StaffType

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface StaffTypeView : BaseView {
    fun onGetStaffTypeSuccess(result: MutableList<StaffType>)
}