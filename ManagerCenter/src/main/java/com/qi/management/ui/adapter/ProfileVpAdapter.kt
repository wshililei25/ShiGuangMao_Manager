package com.yizhipin.goods.ui.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.alibaba.android.arouter.launcher.ARouter
import com.qi.management.ui.fragment.TeacherDatumFragment
import com.qi.management.ui.fragment.TeacherWorksFragment
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.provider.router.RouterPath

/**
 * Created by ${XiLei} on 2018/9/22.
 */
class ProfileVpAdapter(fragmentManager: FragmentManager, val mTeacherUid: String) : FragmentPagerAdapter(fragmentManager) {

    private var mList = mutableListOf("我的资料", "我的作品")

    override fun getItem(position: Int): Fragment {

        val bundle = Bundle()
        bundle.putString(BaseConstant.KEY_TEACHER_UID, mTeacherUid)

        if (position == 0) {
            val fragment = TeacherDatumFragment()
            fragment.arguments = bundle
            return fragment
        }
        if (position == 1) {
            val fragment = ARouter.getInstance().build(RouterPath.UserCenter.TEACHER_WORKS).navigation() as Fragment
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