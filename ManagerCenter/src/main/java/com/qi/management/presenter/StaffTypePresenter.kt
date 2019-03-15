package com.qi.management.presenter

import com.qi.management.presenter.view.StaffTypeView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.usercenter.data.response.StaffType
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class StaffTypePresenter @Inject constructor() : BasePresenter<StaffTypeView>() {

    @Inject
    lateinit var mServiceImpl: ManagerServiceImpl

    fun getStaffTypeList() {
        mServiceImpl.getStaffTypeList().execute(object : BaseSubscriber<MutableList<StaffType>>(mView) {
            override fun onNext(t: MutableList<StaffType>) {
                mView.onGetStaffTypeSuccess(t)
            }
        }, mLifecycleProvider)
    }
}

