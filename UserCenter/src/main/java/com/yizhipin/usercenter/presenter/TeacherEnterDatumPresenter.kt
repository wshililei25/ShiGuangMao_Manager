package com.yizhipin.usercenter.presenter

import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.usercenter.presenter.view.TeacherEnterDatumView
import com.yizhipin.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class TeacherEnterDatumPresenter @Inject constructor() : BasePresenter<TeacherEnterDatumView>() {

    @Inject
    lateinit var mUserServiceImpl: UserServiceImpl

    fun applyEnterDatum(map: MutableMap<String, String>) {
        mView.showLoading()
        mUserServiceImpl.applyEnterDatum(map).execute(object : BaseSubscriber<Teacher>(mView) {
            override fun onNext(t: Teacher) {
                mView.applyEnterDatumSucccess(t)
            }
        }, mLifecycleProvider)
    }

}

