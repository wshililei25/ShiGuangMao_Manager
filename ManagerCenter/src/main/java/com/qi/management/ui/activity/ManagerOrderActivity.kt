package com.qi.management.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.qi.management.R
import com.qi.management.ui.adapter.ManagerOrderVpAdapter
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_manager_order.*

/**
 * Created by ${XiLei} on 2018/9/25.
 * 管理端订单
 */
class ManagerOrderActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_order)

        initView()
    }

    private fun initView() {
        mOrderTab.tabMode = TabLayout.MODE_SCROLLABLE
        mOrderVp.adapter = ManagerOrderVpAdapter(supportFragmentManager, this)
        mOrderTab.setupWithViewPager(mOrderVp)

        mOrderVp.currentItem = intent.getIntExtra(BaseConstant.KEY_SHOP_NAME, 0)
    }
}