package com.yizhipin.usercenter.presenter.view

import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.mvp.view.BaseView

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface InvitationView : BaseView {
    fun getIntegralListSuccess(result: MutableList<UserInfo>)
    fun addIntegralSuccess(result: UserInfo)
}