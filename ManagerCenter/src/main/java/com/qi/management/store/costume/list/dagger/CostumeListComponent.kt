package com.qi.management.store.costume.list.dagger

import com.qi.management.store.costume.list.view.CostumeListChildFragment
import com.qi.management.store.costume.list.view.CostumeListFragment
import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import dagger.Component

/**
 * Creator Qi
 * Date 2019/2/28
 */
@PerComponentScope
@Component(modules = [CostumeListModule::class], dependencies = [ActivityComponent::class])
interface CostumeListComponent {
    fun inject(fragment: CostumeListChildFragment)
    fun inject(fragment: CostumeListFragment)
}