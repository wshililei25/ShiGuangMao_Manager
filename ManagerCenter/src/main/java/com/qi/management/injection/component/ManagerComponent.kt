package com.qi.management.injection.component

import com.qi.management.injection.module.ManagerModule
import com.qi.management.ui.activity.*
import com.qi.management.ui.fragment.*
import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import dagger.Component

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(ManagerModule::class))
interface ManagerComponent {

    fun inject(activity: TeacherListActivity)
    fun inject(activity: TeacherDetailActivity)
    fun inject(activity: TeacherDatumFragment)
    fun inject(activity: TeacherWorksFragment)
    fun inject(activity: TeacherEvaluateFragment)
    fun inject(activity: TeacherCollectFeesFragment)
    fun inject(activity: StaffListActivity)
    fun inject(activity: StaffDetailActivity)
    fun inject(activity: AddStaffActivity)
    fun inject(activity: StaffTypeActivity)
    fun inject(activity: CustomerListActivity)
    fun inject(activity: CustomerDetailActivity)
    fun inject(activity: ManagerOrderFragment)
    fun inject(activity: ManagerOrderDetailsActivity)
    fun inject(activity: WithdrawCashApplyActivity)
    fun inject(activity: ComplainFragment)
    fun inject(activity: NewFragment)
    fun inject(activity: FinancialStatisticsFragment)
    fun inject(activity: StoreInfoManagementActivity)
    fun inject(activity: TeacherApplyActivity)

}