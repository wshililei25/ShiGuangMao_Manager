package com.qi.management.presenter

import com.qi.management.presenter.view.TeacherWorksView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.TeacherWorks
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import javax.inject.Inject

class TeacherWorksPresenter @Inject constructor() : BasePresenter<TeacherWorksView>() {

    @Inject
    lateinit var mServiceImpl: ManagerServiceImpl

    fun getTeacherWorks(map: MutableMap<String, String>) {

        mServiceImpl.getTeacherWorks(map).execute(object : BaseSubscriber<BasePagingResp<MutableList<TeacherWorks>>>(mView) {
            override fun onNext(t: BasePagingResp<MutableList<TeacherWorks>>) {
                mView.onGetTeacherWorksSuccess(t)
            }
        }, mLifecycleProvider)

    }

}
