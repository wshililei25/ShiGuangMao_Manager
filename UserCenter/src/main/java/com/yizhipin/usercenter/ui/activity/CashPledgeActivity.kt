package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.usercenter.R
import kotlinx.android.synthetic.main.activity_cash_pledge.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/9/24.
 * 押金
 */

class CashPledgeActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_pledge)

        initView()
    }

    private fun initView() {
        mBtn.onClick(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mBtn -> ARouter.getInstance().build(RouterPath.PayCenter.PATH_PAY_RECHARGE)
                    .withBoolean(BaseConstant.KEY_IS_FIRST,true).navigation()
        }
    }

}