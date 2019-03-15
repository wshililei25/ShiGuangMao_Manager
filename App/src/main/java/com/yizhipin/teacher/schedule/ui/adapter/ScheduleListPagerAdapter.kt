package com.yizhipin.teacher.schedule.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.yizhipin.teacher.schedule.ui.fragment.ScheduleListChildFragment

/**
 * Creator Qi
 * Date 2018/12/19
 */
class ScheduleListPagerAdapter(private val fragments: Array<ScheduleListChildFragment>, fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
        return fragments[p0]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}