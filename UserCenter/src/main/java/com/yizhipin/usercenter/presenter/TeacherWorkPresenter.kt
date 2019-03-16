package com.yizhipin.usercenter.presenter

import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.Works
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.usercenter.presenter.view.TeacherWorkView
import com.yizhipin.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class TeacherWorkPresenter @Inject constructor() : BasePresenter<TeacherWorkView>() {

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

    fun addWork(map: MutableMap<String, String>) {
        mServiceImpl.addWork(map).execute(object : BaseSubscriber<Works>(mView) {
            override fun onNext(t: Works) {
                mView.onAddWorkSuccess(t)
            }
        }, mLifecycleProvider)
    }

    fun getWorksList(map: MutableMap<String, String>) {
        mServiceImpl.getWorksList(map).execute(object : BaseSubscriber<MutableList<Works>>(mView) {
            override fun onNext(t: MutableList<Works>) {
                mView.onGetWorkListSuccess(t)
            }
        }, mLifecycleProvider)
    }
}

