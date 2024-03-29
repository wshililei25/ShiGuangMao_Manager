package com.yizhipin.usercenter.presenter

import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.usercenter.presenter.view.AuthenticationView
import com.yizhipin.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/18
 */
class AuthenticationPresenter @Inject constructor() : BasePresenter<AuthenticationView>() {

    @Inject
    lateinit var mServiceImpl: UserServiceImpl

    fun getOssSign(map: MutableMap<String, String>) {

        mServiceImpl.getOssSign(map).execute(object : BaseSubscriber<String>(mView) {
            override fun onNext(t: String) {
                mView.onGetOssSignSuccess(t)
            }
        }, mLifecycleProvider)
    }

    fun getOssSignFile(map: MutableMap<String, String>) {

        mServiceImpl.getOssSignFile(map).execute(object : BaseSubscriber<String>(mView) {
            override fun onNext(t: String) {
                mView.onGetOssSignFileSuccess(t)
            }
        }, mLifecycleProvider)
    }

    fun getOssAddress() {
        mServiceImpl.getOssAddress().execute(object : BaseSubscriber<OssAddress>(mView) {
            override fun onNext(t: OssAddress) {
                mView.onGetOssAddressSuccess(t)
            }
        }, mLifecycleProvider)
    }

    fun updateUserInfo(map: MutableMap<String, String>) {
        mServiceImpl.updateUserInfo(map).execute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                mView.onUpdateUserInfoSuccess(t)
            }
        }, mLifecycleProvider)
    }
}