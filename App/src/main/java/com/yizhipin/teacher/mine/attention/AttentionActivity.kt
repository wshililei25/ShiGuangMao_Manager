package com.yizhipin.teacher.mine.attention

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.teacher.mine.attention.teacher.TeacherFragment
import com.yizhipin.teacher.mine.attention.user.UserFragment
import com.yizhipin.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_profile.*

/**
 * Creator Qi
 * Date 2019/1/9
 * <p>我的关注</p>
 */
@Route(path = RouterPath.UserCenter.ATTENTION)
class AttentionActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attention)
//        titleView.setOnLeftIconClickListener { onBackPressed() }
        mTabLayout.addTab(mTabLayout.newTab())
        mTabLayout.addTab(mTabLayout.newTab())
        mTabLayout.setupWithViewPager(viewPager)
//        viewPager.adapter = ProfilePagerAdapter(arrayOf(TeacherFragment(), UserFragment()), supportFragmentManager)
        mTabLayout.getTabAt(0)!!.setText(R.string.teacher)
        mTabLayout.getTabAt(1)!!.setText(R.string.user)
    }

    companion object {
        fun startActivity(fragment: MeFragment) {
            val intent = Intent(fragment.context, AttentionActivity::class.java)
            fragment.startActivity(intent)
        }
    }
}