package com.yizhipin.ui.activity

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.yizhipin.R
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.provider.common.isLogined
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.usercenter.ui.activity.CashPledgeActivity
import com.yizhipin.usercenter.ui.activity.LoginActivity
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/8/19.
 */
class WeclomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        initView()
    }

    private fun initView() {
        if (isLogined()) {
            when (AppPrefsUtils.getInt(BaseConstant.KEY_USER_TYPE)) {
                1 -> {
                    if (AppPrefsUtils.getString(BaseConstant.FOREGIFT).toDouble() <= 0) {
                        startActivity<CashPledgeActivity>()
                    } else {
                        ARouter.getInstance().build(RouterPath.App.PATH_MAIN).navigation()
                    }
                }
                2 -> ARouter.getInstance().build(RouterPath.App.PATH_MAIN_TEACHER).navigation()
            }
        } else {
            startActivity<LoginActivity>()
        }
        finish()
    }


}