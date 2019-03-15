package com.yizhipin.teacher.mine.attention.teacher.dagger

import com.yizhipin.teacher.mine.attention.teacher.TeacherFragment
import com.yizhipin.teacher.mine.attention.teacher.mvp.TeacherContract
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
    fun inject(activity: TeacherFragment)
}

@dagger.Module
class Module(val view: TeacherContract.IView) {

    @Provides
    fun provideView(): TeacherContract.IView {
        return view
    }
}