package com.yizhipin.teacher.mine.paymentdeposit.dagger

import com.yizhipin.teacher.mine.paymentdeposit.mvp.PaymentDepositContract
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
//    fun inject(activity: PaymentDepositActivity)
}

@dagger.Module
class Module(val view: PaymentDepositContract.IView) {

    @Provides
    fun provideView(): PaymentDepositContract.IView {
        return view
    }
}