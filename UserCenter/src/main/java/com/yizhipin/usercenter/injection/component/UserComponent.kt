package com.yizhipin.usercenter.injection.component

import android.support.v4.app.Fragment
import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.injection.component.ActivityComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.ui.activity.*
import dagger.Component

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@PerComponentScope
@Component(dependencies = [ActivityComponent::class], modules = [UserModule::class])
interface UserComponent {

    fun inject(activity: LoginActivity)
    fun inject(activity: RegisterActivity)
    fun inject(activity: UserInfoActivity)
    fun inject(activity: BindMobileActivity)
    fun inject(activity: PayPwdSetActivity)
    fun inject(activity: PayPwdUpdateActivity)
    fun inject(activity: PayPwdResetActivity)
    fun inject(activity: ResetPwdActivity)
    fun inject(activity: AuthenticationActivity)
    fun inject(activity: TeacherEnterDatumActivity)
    fun inject(activity: ShopActivity)
    fun inject(activity: TeacherWorksActivity)
    fun inject(activity: WalletActivity)
    fun inject(activity: InvitationActivity)
    fun inject(activity: InvitationAddActivity)
    fun inject(activity: CreditActivity)
}