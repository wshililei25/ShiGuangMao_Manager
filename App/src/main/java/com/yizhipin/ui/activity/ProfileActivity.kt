package com.yizhipin.ui.activity

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.qi.management.ui.fragment.TeacherDatumFragment
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.teacher.mine.profile.ProductionFragment
import com.yizhipin.teacher.mine.profile.adapter.ProfilePagerAdapter
import com.yizhipin.teacher.mine.profile.mvp.ProfileContract
import com.yizhipin.teacher.mine.profile.mvp.ProfilePresenterImpl
import com.yizhipin.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_profile.*

/**
 * Creator Qi
 * Date 2018/12/30
 * <P>我的资料</p>
 */
@Route(path = RouterPath.UserCenter.PROFILE)
class ProfileActivity : BaseMvpActivity<ProfilePresenterImpl>(), ProfileContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        mTabLayout.addTab(mTabLayout.newTab())
        mTabLayout.addTab(mTabLayout.newTab())
        mTabLayout.setupWithViewPager(viewPager)
        viewPager.adapter = ProfilePagerAdapter(arrayOf(TeacherDatumFragment(), ProductionFragment()), supportFragmentManager)
        mTabLayout.getTabAt(0)!!.setText(R.string.tabProfile)
        mTabLayout.getTabAt(1)!!.setText(R.string.tabWork)
    }

    override fun injectComponent() {

    }


    companion object {
        fun startActivity(fragment: MeFragment) {
            val intent = Intent(fragment.context, ProfileActivity::class.java)
            fragment.startActivity(intent)
        }
    }
}