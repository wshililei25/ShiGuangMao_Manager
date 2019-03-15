package com.qi.management.store.wedding_photography.comboslist.dagger

import com.qi.management.store.wedding_photography.comboslist.view.CombosListFragment
import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import dagger.Component

/**
 * Creator Qi
 * Date 2019/2/24
 */
@PerComponentScope
@Component(modules = [CombosListModule::class], dependencies = [ActivityComponent::class])
interface CombosListComponent {
    fun inject(fragment: CombosListFragment)
}