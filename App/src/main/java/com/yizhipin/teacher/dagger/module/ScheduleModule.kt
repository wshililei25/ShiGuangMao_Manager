package com.yizhipin.teacher.dagger.module

import com.yizhipin.teacher.ScheduleCalendarView
import com.yizhipin.teacher.schedule.presenter.ScheduleCalendarPresenter
import dagger.Module
import dagger.Provides

/**
 * Creator Qi
 * Date 2018/12/13
 */
@Module
class ScheduleModule constructor(var view:ScheduleCalendarView) {

    @Provides
    fun provideSchduleCalendarView():ScheduleCalendarView{
        return view
    }
}