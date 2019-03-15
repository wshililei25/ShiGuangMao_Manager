package com.qi.management.presenter

import com.qi.management.presenter.view.TeacherView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class TeacherPresenter @Inject constructor() : BasePresenter<TeacherView>() {

    @Inject
    lateinit var mServiceImpl: ManagerServiceImpl

    fun getCameramanList(map: MutableMap<String, String>) {
        mServiceImpl.getCameramanList(map).execute(object : BaseSubscriber<BasePagingResp<MutableList<Teacher>>>(mView) {
            override fun onNext(t: BasePagingResp<MutableList<Teacher>>) {
                mView.onGetTeacherListSuccess(t)
            }
        }, mLifecycleProvider)
    }

    fun getCameramanDetails(map: MutableMap<String, String>) {
        mView.showLoading()
        mServiceImpl.getCameramanDetails(map).execute(object : BaseSubscriber<Teacher>(mView) {
            override fun onNext(t: Teacher) {
                mView.onGetCameramanDetailsSuccess(t)
            }
        }, mLifecycleProvider)
    }
}