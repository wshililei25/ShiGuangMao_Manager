package com.qi.management.presenter

import com.qi.management.presenter.view.AddStaffView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class AddStaffPresenter @Inject constructor() : BasePresenter<AddStaffView>() {

    @Inject
    lateinit var mServiceImpl: ManagerServiceImpl

    fun addStaff(map: MutableMap<String, String>) {
        mView.showLoading()
        mServiceImpl.addStaff(map).execute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                mView.onAddStaffSuccess(t)
            }
        }, mLifecycleProvider)
    }


}