package com.yizhipin.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.qi.management.home.stores.HomeGridFragment
import com.yizhipin.R
import com.yizhipin.base.common.AppManager
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.ui.fragment.ChatListFragment
import com.yizhipin.ui.fragment.FinancialAffairsFragment
import com.yizhipin.ui.fragment.MeFragment
import com.yizhipin.ui.fragment.PersonnelFragment
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.toast
import java.util.*

/**
 * 管理端主页
 */
@Route(path = RouterPath.App.PATH_MAIN_TEACHER)
class MainTeacherActivity : BaseActivity() {

    private val mStack = Stack<Fragment>()
    private val mHomeGridFragment by lazy { HomeGridFragment() }
    private val mPersonnelFragment by lazy { PersonnelFragment() }
    private val mChatListFragment by lazy { ChatListFragment() }
    private val mFinancialAffairsFragment by lazy { FinancialAffairsFragment() }
    private val mMeFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initFragment()
        initBottomNav()
        changeFragment(0)
    }

    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mContaier, mHomeGridFragment)
        manager.add(R.id.mContaier, mPersonnelFragment)
        manager.add(R.id.mContaier, mChatListFragment)
        manager.add(R.id.mContaier, mFinancialAffairsFragment)
        manager.add(R.id.mContaier, mMeFragment)
        manager.commit()

        mStack.add(mHomeGridFragment)
        mStack.add(mPersonnelFragment)
        mStack.add(mChatListFragment)
        mStack.add(mFinancialAffairsFragment)
        mStack.add(mMeFragment)
    }

    private fun initBottomNav() {
        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }

        })
    }

    private fun changeFragment(position: Int) {
        val manager = supportFragmentManager.beginTransaction()
        for (fragment in mStack) {
            manager.hide(fragment)
        }
        manager.show(mStack[position])
        manager.commit()
    }

    private var mPressTime: Long = 0
    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - mPressTime > 2000) {
            toast("再按一次退出App")
            mPressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }
}
