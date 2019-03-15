package com.yizhipin.teacher.mine.attention.user.dagger

import com.yizhipin.teacher.mine.attention.user.UserFragment
import com.yizhipin.teacher.mine.attention.user.mvp.UserContract
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
    fun inject(activity: UserFragment)
}

@dagger.Module
class Module(val view: UserContract.IView) {

    @Provides
    fun provideView(): UserContract.IView {
        return view
    }
}