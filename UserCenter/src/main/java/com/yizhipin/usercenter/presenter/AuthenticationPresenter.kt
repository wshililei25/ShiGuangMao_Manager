package com.yizhipin.usercenter.presenter

import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.usercenter.presenter.view.AuthenticationView
import com.yizhipin.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/18
 */
class AuthenticationPresenter @Inject constructor() : BasePresenter<AuthenticationView>() {

    @Inject
    lateinit var mUserServiceImpl: UserServiceImpl

}