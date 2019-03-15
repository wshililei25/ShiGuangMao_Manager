package com.yizhipin.usercenter.me.dagger

import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.usercenter.injection.module.UserModule
import dagger.Component

/**
 * Creator Qi
 * Date 2019/1/13
 */
@PerComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [UserModule::class])
interface MeComponent {
//    fun inject(fragment: MeFragment)
}