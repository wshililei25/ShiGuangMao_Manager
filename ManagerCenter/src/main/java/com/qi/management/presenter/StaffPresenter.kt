package com.qi.management.presenter

import com.qi.management.presenter.view.StaffView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class StaffPresenter @Inject constructor() : BasePresenter<StaffView>() {

    @Inject
    lateinit var mServiceImpl: ManagerServiceImpl

    fun getStaffList(map: MutableMap<String, String>) {
        mServiceImpl.getStaffList(map).execute(object : BaseSubscriber<MutableList<UserInfo>>(mView) {
            override fun onNext(t: MutableList<UserInfo>) {
                mView.onGetStaffListSuccess(t)
            }
        }, mLifecycleProvider)
    }


}