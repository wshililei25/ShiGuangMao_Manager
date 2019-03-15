package com.yizhipin.teacher.mine.password.dagger

import com.yizhipin.teacher.mine.password.PasswordModificationActivity
import com.yizhipin.teacher.mine.password.mvp.PasswordModificationContract
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
    fun inject(activity: PasswordModificationActivity)
}

@dagger.Module
class Module(val view: PasswordModificationContract.IView) {

    @Provides
    fun provideView(): PasswordModificationContract.IView {
        return view
    }
}