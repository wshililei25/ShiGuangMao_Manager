package com.yizhipin.teacher.mine.baseinfo.dagger

import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.teacher.mine.baseinfo.mvp.BaseInfoContract
import com.yizhipin.teacher.mine.baseinfo.BaseInfoActivity
import dagger.Component
import dagger.Module
import dagger.Provides

/**
 * Creator Qi
 * Date 2018/12/30
 */
@PerComponentScope
@Component(modules = [BaseInfoModule::class], dependencies = [ActivityComponent::class])
interface BaseInfoComponent {
    fun inject(activity: BaseInfoActivity)
}

@Module
class BaseInfoModule(val view: BaseInfoContract.BaseInfoView) {

    @Provides
    fun provideView(): BaseInfoContract.BaseInfoView {
        return view
    }
}