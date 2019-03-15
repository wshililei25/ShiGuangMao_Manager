package com.yizhipin.teacher.mine.phone.dagger

import com.yizhipin.teacher.mine.phone.mvp.PhoneContract
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
//    fun inject(activity: PhoneActivity)
}

@dagger.Module
class Module(val view: PhoneContract.IView) {

    @Provides
    fun provideView(): PhoneContract.IView {
        return view
    }
}