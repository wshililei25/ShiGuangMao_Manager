package com.yizhipin.usercenter.presenter

import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.base.utils.BasePrefsUtils
import com.yizhipin.usercenter.presenter.view.LoginView
import com.yizhipin.usercenter.service.impl.MainServiceImpl
import com.yizhipin.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    lateinit var mUserServiceImpl: UserServiceImpl

    @Inject
    lateinit var mServiceImpl: MainServiceImpl

    fun login(map: MutableMap<String, String>) {

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        mUserServiceImpl.login(map)
                .execute(object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        mView.onLoginSuccess(t)
                    }
                }, mLifecycleProvider)

    }

    fun getOssAddress() {
//        mView.showLoading()
        mServiceImpl.getOssAddress()
                .execute(object : BaseSubscriber<OssAddress>(mView) {
                    override fun onNext(t: OssAddress) {
                        BasePrefsUtils.putOssInfo(t)
                    }
                }, mLifecycleProvider)

    }
}