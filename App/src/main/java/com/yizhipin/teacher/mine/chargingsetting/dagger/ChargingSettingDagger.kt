package com.yizhipin.teacher.mine.chargingsetting.dagger

import com.yizhipin.teacher.mine.chargingsetting.mvp.ChargingSettingContract
import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import dagger.Provides

/**
 * Creator Qi
 * Date 2018/12/30
 */
@PerComponentScope
@dagger.Component(modules = [Module::class], dependencies = [ActivityComponent::class])
interface Component {
//    fun inject(activity: ChargingSettingActivity)
}

@dagger.Module
class Module(val view: ChargingSettingContract.IView) {

    @Provides
    fun provideView(): ChargingSettingContract.IView {
        return view
    }
}