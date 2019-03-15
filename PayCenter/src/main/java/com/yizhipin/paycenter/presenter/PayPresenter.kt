package com.yizhipin.shop.presenter

import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.shop.presenter.view.PayView
import com.yizhipin.shop.service.impl.PayServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class PayPresenter @Inject constructor() : BasePresenter<PayView>() {

    @Inject
    lateinit var mServiceImpl: PayServiceImpl

    fun rechargeCashPledge(map: MutableMap<String, String>) {
        mView.showLoading()
        mServiceImpl.rechargeCashPledge(map).execute(object : BaseSubscriber<String>(mView) {
            override fun onNext(t: String) {
                mView.onRechargeSuccess(t)
            }
        }, mLifecycleProvider)
    }
}

