package com.yizhipin.goods.ui.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.qi.management.ui.fragment.TeacherCollectFeesFragment
import com.qi.management.ui.fragment.TeacherDatumFragment
import com.qi.management.ui.fragment.TeacherEvaluateFragment
import com.qi.management.ui.fragment.TeacherWorksFragment
import com.yizhipin.base.common.BaseConstant

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class TeacherVpAdapter(fragmentManager: FragmentManager, val mTeacherUid: String) : FragmentPagerAdapter(fragmentManager) {

    private var mList = mutableListOf("资料", "作品", "评价", "收费")

    override fun getItem(position: Int): Fragment {

        val bundle = Bundle()
        bundle.putString(BaseConstant.KEY_TEACHER_UID, mTeacherUid)

        if (position == 0) {
            val fragment = TeacherDatumFragment()
            fragment.arguments = bundle
            return fragment
        }
        if (position == 1) {
            val fragment = TeacherWorksFragment()
            fragment.arguments = bundle
            return fragment
        }
        if (position == 2) {
            val fragment = TeacherEvaluateFragment()
            fragment.arguments = bundle
            return fragment
        }
        if (position == 3) {
            val fragment = TeacherCollectFeesFragment()
            fragment.arguments = bundle
            return fragment
        }
        return null!!
    }

    override fun getCount(): Int {
        return mList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mList[position]
    }


}