package com.qi.management.store.production_management.dagger

import com.qi.management.store.production_management.view.ProductionListActivity
import com.qi.management.store.production_management.view.ProductionListFragment
import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import dagger.Component

/**
 * Creator Qi
 * Date 2019/2/26
 */
@PerComponentScope
@Component(modules = [ProductionListModule::class], dependencies = [ActivityComponent::class])
interface ProductionListComponent {
    fun inject(fragment: ProductionListFragment)
    fun inject(activity: ProductionListActivity)
}