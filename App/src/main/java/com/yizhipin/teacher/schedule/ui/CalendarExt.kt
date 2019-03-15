package com.yizhipin.teacher.schedule.ui

import android.view.View
import com.haibin.calendarview.CalendarView
import com.yizhipin.data.response.ScheduleItemBean

/**
 * Creator Qi
 * Date 2018/12/15
 */
fun CalendarView.update(scheduleMap: Map<String, ScheduleItemBean>) {
    mDelegate.scheduleMap = scheduleMap
    val view = monthViewPager.findViewWithTag<View>(monthViewPager.currentItem)
    view?.invalidate()
}