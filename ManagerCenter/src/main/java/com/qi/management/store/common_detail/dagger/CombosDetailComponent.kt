package com.qi.management.store.common_detail.dagger

import com.qi.management.store.common_detail.view.CommonDetailActivity
import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import dagger.Component

/**
 * Creator Qi
 * Date 2019/2/24
 */
@PerComponentScope
@Component(modules = [CombosDetailModule::class], dependencies = [ActivityComponent::class])
interface CombosDetailComponent {
    fun inject(activity: CommonDetailActivity)
}