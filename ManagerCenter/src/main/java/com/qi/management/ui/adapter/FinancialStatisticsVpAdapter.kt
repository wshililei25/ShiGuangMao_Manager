package com.qi.management.ui.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.qi.management.common.ComplainStatus
import com.qi.management.common.FinancialStatus
import com.qi.management.ui.fragment.ComplainFragment
import com.qi.management.ui.fragment.FinancialStatisticsFragment
import com.yizhipin.base.common.BaseConstant

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class FinancialStatisticsVpAdapter(fragmentManager: FragmentManager, context: Context) : FragmentPagerAdapter(fragmentManager) {

    private val mTitles = arrayOf("日", "月", "年", "全部")

    override fun getItem(position: Int): Fragment {
        val fragment = FinancialStatisticsFragment()
        val bunder = Bundle()
        when (position) {
            0 -> bunder.putString(BaseConstant.KEY_ORDER_STATUS, FinancialStatus.FINANCIAL_DAY)
            1 -> bunder.putString(BaseConstant.KEY_ORDER_STATUS, FinancialStatus.FINANCIAL_MONTH)
            2 -> bunder.putString(BaseConstant.KEY_ORDER_STATUS, FinancialStatus.FINANCIAL_YEAR)
            3 -> bunder.putString(BaseConstant.KEY_ORDER_STATUS, FinancialStatus.FINANCIAL_ALL)
        }
        fragment.arguments = bunder
        return fragment
    }

    override fun getCount(): Int {
        return mTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mTitles[position]
    }
}