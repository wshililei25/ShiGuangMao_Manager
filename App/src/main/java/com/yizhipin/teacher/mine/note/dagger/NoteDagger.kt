package com.yizhipin.teacher.mine.note.dagger

import com.yizhipin.teacher.mine.note.NoteActivity
import com.yizhipin.teacher.mine.note.mvp.NoteContract
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
    fun inject(activity: NoteActivity)
}

@dagger.Module
class Module(val view: NoteContract.IView) {

    @Provides
    fun provideView(): NoteContract.IView {
        return view
    }
}