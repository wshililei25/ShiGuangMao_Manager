package com.yizhipin.teacher.dagger.component

import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.teacher.dagger.module.ScheduleListModule
import com.yizhipin.teacher.dagger.module.ScheduleModule
import com.yizhipin.teacher.schedule.ui.fragment.ScheduleCalendarFragment
import com.yizhipin.teacher.schedule.ui.fragment.ScheduleFragment
import com.yizhipin.teacher.schedule.ui.fragment.ScheduleListChildFragment
import dagger.Component

/**
 * Creator Qi
 * Date 2018/12/14
 */
@PerComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [ScheduleModule::class])
interface ScheduleComponent {
    fun inject(scheduleFragment: ScheduleFragment)
    fun inject(scheduleCalendarFragment: ScheduleCalendarFragment)
}
@PerComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [ScheduleListModule::class])
interface ScheduleListComponent {
    fun inject(scheduleListFragment: ScheduleListChildFragment)
}