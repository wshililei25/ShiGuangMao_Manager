package com.yizhipin.teacher.dagger.module

import com.yizhipin.teacher.ScheduleListView
import dagger.Module
import dagger.Provides

/**
 * Creator Qi
 * Date 2018/12/19
 */
@Module
class ScheduleListModule(val view: ScheduleListView){
    @Provides
    fun provideView():ScheduleListView{
        return view
    }
}