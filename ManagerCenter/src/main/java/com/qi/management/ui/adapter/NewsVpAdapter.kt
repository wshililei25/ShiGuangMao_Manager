package com.qi.management.ui.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.qi.management.common.NewStatus
import com.qi.management.ui.fragment.NewFragment
import com.yizhipin.base.common.BaseConstant

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class NewsVpAdapter(fragmentManager: FragmentManager, context: Context) : FragmentPagerAdapter(fragmentManager) {

    private val mTitles = arrayOf("全部推送", "个人推送")

    override fun getItem(position: Int): Fragment {
        val fragment = NewFragment()
        val bunder = Bundle()
        when (position) {
            0 -> bunder.putString(BaseConstant.KEY_ORDER_STATUS, NewStatus.NEWS_ALL)
            1 -> bunder.putString(BaseConstant.KEY_ORDER_STATUS, NewStatus.NEWS_PERSONAL)
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