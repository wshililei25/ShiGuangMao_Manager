package com.qi.management.presenter.view

import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.mvp.view.BaseView

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface AddStaffView : BaseView {
    fun onAddStaffSuccess(result: UserInfo)
}