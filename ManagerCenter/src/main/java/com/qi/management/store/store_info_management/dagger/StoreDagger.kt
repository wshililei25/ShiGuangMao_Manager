package com.qi.management.store.store_info_management.dagger

import com.qi.management.store.store_info_management.StoreInfoManagementActivity
import com.qi.management.store.store_info_management.mvp.StoresContract
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
    fun inject(activity: StoreInfoManagementActivity)
}

@dagger.Module
class Module(val view: StoresContract.IView) {

    @Provides
    fun provideView(): StoresContract.IView {
        return view
    }
}