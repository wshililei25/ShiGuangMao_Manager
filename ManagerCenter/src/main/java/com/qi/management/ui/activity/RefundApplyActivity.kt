package com.qi.management.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.qi.management.R
import com.qi.management.ui.adapter.RefundApplyVpAdapter
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_refund_apply.*

/**
 * Created by ${XiLei} on 2018/9/25.
 * 退款申请
 */
class RefundApplyActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_refund_apply)

        initView()
    }

    private fun initView() {
        mOrderTab.tabMode = TabLayout.MODE_SCROLLABLE
        mOrderVp.adapter = RefundApplyVpAdapter(supportFragmentManager, this)
        mOrderTab.setupWithViewPager(mOrderVp)

        mOrderVp.currentItem = intent.getIntExtra(BaseConstant.KEY_SHOP_NAME, 0)
    }
}