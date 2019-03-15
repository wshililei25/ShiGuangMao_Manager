package com.yizhipin.goods.presenter.view

import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.mvp.view.BaseView

interface UserView : BaseView {

    fun onGetUserDetailsSuccess(result: UserInfo)
    fun onGetCrowdorderListSuccess(result: MutableList<UserInfo>)
}
