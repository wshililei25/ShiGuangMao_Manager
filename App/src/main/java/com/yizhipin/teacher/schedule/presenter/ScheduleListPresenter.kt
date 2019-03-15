package com.yizhipin.teacher.schedule.presenter

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.CodeListHandlerSubscriber
import com.yizhipin.data.response.ScheduleItemBean
import com.yizhipin.teacher.ScheduleListView
import com.yizhipin.teacher.schedule.model.ScheduleModel
import com.yizhipin.usercenter.utils.UserPrefsUtils
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/8
 */
class ScheduleListPresenter @Inject constructor(val model: ScheduleModel, val view: ScheduleListView) : BasePresenter<ScheduleListView>(view) {

    fun getScheduleList(pageIndex:Int,status: Int) {
        model.getScheduleList(UserPrefsUtils.getUserId(), status, pageIndex).execute(object : CodeListHandlerSubscriber<MutableList<ScheduleItemBean>>(mView){
            override fun onSucceed(data: BasePagingResp<MutableList<ScheduleItemBean>>) {
                mView.show(data)
            }
        },mLifecycleProvider)
    }
}