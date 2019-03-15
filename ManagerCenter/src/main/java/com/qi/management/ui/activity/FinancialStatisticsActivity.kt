package com.qi.management.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.qi.management.R
import com.qi.management.ui.adapter.ComplainVpAdapter
import com.qi.management.ui.adapter.FinancialStatisticsVpAdapter
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_financial_statistics.*

/**
 * Created by ${XiLei} on 2018/9/25.
 * 财务统计
 */
class FinancialStatisticsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_financial_statistics)

        initView()
    }

    private fun initView() {
        mOrderTab.tabMode = TabLayout.MODE_FIXED
        mOrderVp.adapter = FinancialStatisticsVpAdapter(supportFragmentManager, this)
        mOrderTab.setupWithViewPager(mOrderVp)

        mOrderVp.currentItem = intent.getIntExtra(BaseConstant.KEY_SHOP_NAME, 0)
    }
}