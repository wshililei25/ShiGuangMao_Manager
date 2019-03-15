package com.qi.management.presenter

import com.qi.management.presenter.view.StaffDetailsView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.data.response.ManagerOrder
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class StaffDetailsPresenter @Inject constructor() : BasePresenter<StaffDetailsView>() {

    @Inject
    lateinit var mServiceImpl: ManagerServiceImpl

    fun getStaffOrder(map: MutableMap<String, String>) {
        mView.showLoading()
        mServiceImpl.getStaffOrder(map).execute(object : BaseSubscriber<MutableList<ManagerOrder>>(mView) {
            override fun onNext(t: MutableList<ManagerOrder>) {
                mView.onGetStaffDetailsSuccess(t)
            }
        }, mLifecycleProvider)
    }


}