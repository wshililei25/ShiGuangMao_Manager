package com.yizhipin.teacher.mine.depositrefund.dagger

import com.yizhipin.teacher.mine.depositrefund.DepositRefundActivity
import com.yizhipin.teacher.mine.depositrefund.mvp.DepositRefundContract
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
    fun inject(activity: DepositRefundActivity)
}

@dagger.Module
class Module(val view: DepositRefundContract.IView) {

    @Provides
    fun provideView(): DepositRefundContract.IView {
        return view
    }
}