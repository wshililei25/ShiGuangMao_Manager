package com.qi.management.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.qi.management.R
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_my_cash_pledge.*

/**
 * Created by ${XiLei} on 2018/9/24.
 *
 * 我的押金
 */
class MyCashPledgeActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_cash_pledge)

        initView()
    }

    private fun initView() {
        mRebackBtn.onClick(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mRebackBtn -> ARouter.getInstance().build(RouterPath.PayCenter.PATH_PAY_WITHDRAW)
                    .withBoolean(BaseConstant.KEY_IS_CASH, true).navigation()
        }
    }

}