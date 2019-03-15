package com.qi.management.presenter

import com.qi.management.presenter.view.ManagerOrderView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.ManagerOrder
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import javax.inject.Inject

class ManagerOrderPresenter @Inject constructor() : BasePresenter<ManagerOrderView>() {

    @Inject
    lateinit var mOrderServiceImpl: ManagerServiceImpl

    fun getOrderList(map: MutableMap<String, String>) {

        mOrderServiceImpl.getOrderList(map).execute(object : BaseSubscriber<BasePagingResp<MutableList<ManagerOrder>>>(mView) {
            override fun onNext(t: BasePagingResp<MutableList<ManagerOrder>>) {
                mView.onGetOrderListResult(t)
            }
        }, mLifecycleProvider)

    }

}
