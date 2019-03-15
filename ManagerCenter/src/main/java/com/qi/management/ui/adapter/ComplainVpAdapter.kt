package com.qi.management.ui.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.qi.management.common.ComplainStatus
import com.qi.management.ui.fragment.ComplainFragment
import com.yizhipin.base.common.BaseConstant

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class ComplainVpAdapter(fragmentManager: FragmentManager, context: Context) : FragmentPagerAdapter(fragmentManager) {

    private val mTitles = arrayOf("全部问题", "常规问题", "服务问题", "老师问题")

    override fun getItem(position: Int): Fragment {
        val fragment = ComplainFragment()
        val bunder = Bundle()
        when (position) {
            0 -> bunder.putString(BaseConstant.KEY_ORDER_STATUS, ComplainStatus.COMPLAIN_ALL)
            1 -> bunder.putString(BaseConstant.KEY_ORDER_STATUS, ComplainStatus.COMPLAIN_COMMON)
            2 -> bunder.putString(BaseConstant.KEY_ORDER_STATUS, ComplainStatus.COMPLAIN_SERVICE)
            3 -> bunder.putString(BaseConstant.KEY_ORDER_STATUS, ComplainStatus.COMPLAIN_TEACHER)
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