    package com.qi.management.ui.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.qi.management.common.OrderStatus
import com.qi.management.ui.fragment.ManagerOrderFragment
import com.yizhipin.base.common.BaseConstant

    /**
 * Created by ${XiLei} on 2018/9/22.
 */
class ManagerOrderVpAdapter(fragmentManager: FragmentManager, context: Context) : FragmentPagerAdapter(fragmentManager) {

    private val mTitles = arrayOf( "待完成", "已完成")

    override fun getItem(position: Int): Fragment {
        val fragment = ManagerOrderFragment()
        val bunder = Bundle()
        when (position) {
            0 -> bunder.putString(BaseConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_WAIT_FINISH)
            1 -> bunder.putString(BaseConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_FINISHED)
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