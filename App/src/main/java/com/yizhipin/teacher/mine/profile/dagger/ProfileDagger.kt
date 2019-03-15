package com.yizhipin.teacher.mine.profile.dagger

import com.yizhipin.ui.activity.ProfileActivity
import com.yizhipin.teacher.mine.profile.mvp.ProfileContract
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
    fun inject(activity: ProfileActivity)
}

@dagger.Module
class Module(val view: ProfileContract.IView) {

    @Provides
    fun provideView(): ProfileContract.IView {
        return view
    }
}