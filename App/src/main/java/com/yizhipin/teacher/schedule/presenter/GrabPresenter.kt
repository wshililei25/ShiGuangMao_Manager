package com.yizhipin.teacher.schedule.presenter

import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.teacher.GrabView
import com.yizhipin.usercenter.service.impl.MainServiceImpl
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/4
 * <p>抢单</p>
 */
class GrabPresenter @Inject constructor() : BasePresenter<GrabView>() {
    @Inject
    lateinit var mServiceImpl: MainServiceImpl

/*    fun getOrderList() {
        mServiceImpl.getOrderList().execute(object : BaseSubscriber<MutableList<ScheduleItemBean>>(mView) {
            override fun onNext(t: MutableList<ScheduleItemBean>) {
                mView.showData(t)
            }
        }, mLifecycleProvider)
    }*/
}