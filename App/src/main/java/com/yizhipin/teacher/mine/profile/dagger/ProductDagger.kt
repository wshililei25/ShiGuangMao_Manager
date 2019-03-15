package com.yizhipin.teacher.mine.profile.dagger

import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.teacher.mine.profile.ProductionFragment
import com.yizhipin.teacher.mine.profile.mvp.ProductContract
import dagger.Provides

/**
 * Creator Qi
 * Date 2018/12/30
 */
@PerComponentScope
@dagger.Component(modules = [ProductModule::class], dependencies = [ActivityComponent::class])
interface ProductComponent {
    fun inject(fragment: ProductionFragment)
}

@dagger.Module
class ProductModule(val view: ProductContract.IView) {

    @Provides
    fun provideView(): ProductContract.IView {
        return view
    }
}