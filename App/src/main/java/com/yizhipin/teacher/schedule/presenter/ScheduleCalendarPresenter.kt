package com.yizhipin.teacher.schedule.presenter

import android.text.TextUtils
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.ext.convert
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.data.response.ScheduleItemBean
import com.yizhipin.teacher.ScheduleCalendarView
import com.yizhipin.teacher.schedule.model.ScheduleModel
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/8
 * <p>排期日历展示</p>
 */
class ScheduleCalendarPresenter @Inject constructor(var model: ScheduleModel, view: ScheduleCalendarView) : BasePresenter<ScheduleCalendarView>(view) {

    private val scheduleMap: HashMap<String, ScheduleItemBean> = HashMap()

    fun getScheduleByDate(date: String): ScheduleItemBean? {
        return scheduleMap[date]
    }

    fun addSchedule(schedule: ScheduleItemBean) {
        var scheduleDate = schedule.scheduleDate
        if (!TextUtils.isEmpty(scheduleDate)) {
            if (scheduleDate.contains(" ")) {
                scheduleDate = scheduleDate.split(" ")[0]
            } else if (scheduleDate.contains("T")) {
                scheduleDate = scheduleDate.split("T")[0]
            }
            scheduleMap[scheduleDate] = schedule
        }
    }

    fun removeSchedule(schedule: ScheduleItemBean) {
        var scheduleDate = schedule.scheduleDate
        if (!TextUtils.isEmpty(scheduleDate)) {
            if (scheduleDate.contains(" ")) {
                scheduleDate = scheduleDate.split(" ")[0]
            } else if (scheduleDate.contains("T")) {
                scheduleDate = scheduleDate.split("T")[0]
            }
            scheduleMap.remove(scheduleDate)
        }
    }

    fun setScheduleList(scheduleList: List<ScheduleItemBean>, onAddOverListener: () -> Unit) {
        scheduleMap.clear()
        scheduleList.forEach {
            var scheduleDate = it.scheduleDate
            if (!TextUtils.isEmpty(scheduleDate)) {
                if (scheduleDate.contains(" ")) {
                    scheduleDate = scheduleDate.split(" ")[0]
                } else if (scheduleDate.contains("T")) {
                    scheduleDate = scheduleDate.split("T")[0]
                }
                scheduleMap[scheduleDate] = it
            }
        }
        onAddOverListener()
    }

    /**不接受预约*/
    fun cancelSchedule(scheduleDate: String, teacherId: String) {
        model.cancelSchedule(scheduleDate, teacherId).convert().execute(object : BaseSubscriber<ScheduleItemBean>(mView) {
            override fun onNext(t: ScheduleItemBean) {
                super.onNext(t)
                addSchedule(t)
                mView.uploadSchedule(scheduleMap)
            }
        }, mLifecycleProvider)
    }

    /**更新行程*/
    fun updateSchedule(itemBean: ScheduleItemBean) {
        model.updateSchedule(itemBean).convert().execute(object : BaseSubscriber<ScheduleItemBean>(mView) {
            override fun onNext(t: ScheduleItemBean) {
                super.onNext(t)
                removeSchedule(itemBean)
                addSchedule(itemBean)
                mView.uploadSchedule(scheduleMap)
            }
        }, mLifecycleProvider)
    }

    fun getScheduleListFromNet(teacherId: String) {
        model.getScheduleListFromNet(teacherId).convert().execute(object : BaseSubscriber<List<ScheduleItemBean>>(mView) {
            override fun onNext(t: List<ScheduleItemBean>) {
                super.onNext(t)
                setScheduleList(t) { mView.uploadSchedule(scheduleMap) }
            }
        }, mLifecycleProvider)
    }

    fun deleteScheduleFromNet(itemBean: ScheduleItemBean) {
        model.deleteSchedule(itemBean.id).execute(object : BaseSubscriber<BaseResp<Unit>>(mView) {
            override fun onNext(t: BaseResp<Unit>) {
                super.onNext(t)
                if ("00" == t.code) {
                    removeSchedule(itemBean)
                    mView.uploadSchedule(scheduleMap)
                }
            }
        }, mLifecycleProvider)
    }

}