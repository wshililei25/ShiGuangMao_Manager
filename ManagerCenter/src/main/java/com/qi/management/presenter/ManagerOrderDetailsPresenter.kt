package com.qi.management.presenter

import com.qi.management.presenter.view.ManagerOrderDetailsView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.usercenter.data.response.ManagerOrderDetails
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class ManagerOrderDetailsPresenter @Inject constructor() : BasePresenter<ManagerOrderDetailsView>() {

    @Inject
    lateinit var mServiceImpl: ManagerServiceImpl

    fun getManagerOrderDetails(map: MutableMap<String, String>) {
        mView.showLoading()
        mServiceImpl.getManagerOrderDetails(map).execute(object : BaseSubscriber<ManagerOrderDetails>(mView) {
            override fun onNext(t: ManagerOrderDetails) {
                mView.onGetManagerOrderDetailsSuccess(t)
            }
        }, mLifecycleProvider)
    }
}

