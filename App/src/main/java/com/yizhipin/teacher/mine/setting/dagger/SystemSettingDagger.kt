package com.yizhipin.teacher.mine.setting.dagger

import com.yizhipin.teacher.mine.setting.SystemSettingActivity
import com.yizhipin.teacher.mine.setting.mvp.SystemSettingContract
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
    fun inject(activity: SystemSettingActivity)
}

@dagger.Module
class Module(val view: SystemSettingContract.IView) {

    @Provides
    fun provideView(): SystemSettingContract.IView {
        return view
    }
}