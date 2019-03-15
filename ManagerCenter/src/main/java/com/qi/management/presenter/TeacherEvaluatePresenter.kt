package com.qi.management.presenter

import com.qi.management.presenter.view.TeacherEvaluateView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.TeacherWorks
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import javax.inject.Inject

class TeacherEvaluatePresenter @Inject constructor() : BasePresenter<TeacherEvaluateView>() {

    @Inject
    lateinit var mServiceImpl: ManagerServiceImpl

    fun getTeacherEvaluate(map: MutableMap<String, String>) {

        mServiceImpl.getTeacherEvaluate(map).execute(object : BaseSubscriber<BasePagingResp<MutableList<TeacherWorks>>>(mView) {
            override fun onNext(t: BasePagingResp<MutableList<TeacherWorks>>) {
                mView.onGetTeacherWorksSuccess(t)
            }
        }, mLifecycleProvider)

    }

}
