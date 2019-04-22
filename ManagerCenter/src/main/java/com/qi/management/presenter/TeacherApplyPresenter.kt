package com.qi.management.presenter

import com.qi.management.presenter.view.TeacherApplyView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class TeacherApplyPresenter @Inject constructor() : BasePresenter<TeacherApplyView>() {

    @Inject
    lateinit var mServiceImpl: ManagerServiceImpl

    fun getTeacherApplyList(map: MutableMap<String, String>) {
        mServiceImpl.getTeacherApplyList(map).execute(object : BaseSubscriber<MutableList<Teacher>>(mView) {
            override fun onNext(t: MutableList<Teacher>) {
                mView.onGetTeacherApplyListSuccess(t)
            }
        }, mLifecycleProvider)
    }
    fun approveTeacherApply(map: MutableMap<String, String>) {
        mView.showLoading()
        mServiceImpl.approveTeacherApply(map).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onApproveTeacherApplySuccess(t)
            }
        }, mLifecycleProvider)
    }


}