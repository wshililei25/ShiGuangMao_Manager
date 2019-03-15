package com.qi.management.presenter

import com.qi.management.presenter.view.TeacherDatumView
import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.usercenter.service.impl.MainServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class TeacherDatumPresenter @Inject constructor() : BasePresenter<TeacherDatumView>() {

    @Inject
    lateinit var mUserServiceImpl: MainServiceImpl

    fun getTeacherDatum(map: MutableMap<String, String>) {
        mView.showLoading()
        mUserServiceImpl.getTeacherDatum(map).execute(object : BaseSubscriber<Teacher>(mView) {
            override fun onNext(t: Teacher) {
                mView.onGetTeacherDatumSuccess(t)
            }
        }, mLifecycleProvider)
    }

}

