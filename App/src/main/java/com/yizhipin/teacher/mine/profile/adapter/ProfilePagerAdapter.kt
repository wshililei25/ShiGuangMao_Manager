package com.yizhipin.teacher.mine.profile.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Creator Qi
 * Date 2019/1/8
 *
 */
class ProfilePagerAdapter(private val fragments: Array<Fragment>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {
        return fragments[p0]
    }

    override fun getCount(): Int {
        return fragments.size
    }

}