package com.yizhipin.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.yizhipin.R
import com.yizhipin.base.common.AppManager
import com.yizhipin.base.event.HomeIntentEvent
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.teacher.schedule.ui.fragment.ScheduleFragment
import com.yizhipin.ui.activity.IntentParams.EXIST
import com.yizhipin.ui.fragment.ChatListFragment
import com.yizhipin.ui.fragment.MeFragment
import com.yizhipin.usercenter.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*

/**
 * 老师端主页
 */
@Route(path = RouterPath.App.PATH_MAIN)
class MainActivity : BaseActivity() {

    private val mStack = Stack<Fragment>()
    private val mScheduleFragment by lazy { ScheduleFragment() }
    private val mChatListFragment by lazy { ChatListFragment() }
    private val mMeFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        initBottomNav()
        changeFragment(0)
        initObserve()
    }

    private fun initFragment() {
        val manager = supportFragmentManager.beginTransaction()
        manager.add(R.id.mContaier, mScheduleFragment)
        manager.add(R.id.mContaier, mChatListFragment)
        manager.add(R.id.mContaier, mMeFragment)
        manager.commit()

        mStack.add(mScheduleFragment)
        mStack.add(mChatListFragment)
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

    private fun initObserve() {
        Bus.observe<HomeIntentEvent>()
                .subscribe { t: HomeIntentEvent ->
                    run {

                        if (t.position == 3) {
                            changeFragment(2)
                            mBottomNavBar.selectTab(2)
                        } else {
                            changeFragment(1)
                            mBottomNavBar.selectTab(1)
                        }
                    }
                }.registerInBus(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
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

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null && intent.hasExtra(EXIST)) {//判断其他Activity启动本Activity时传递来的intent是否为空
            val isExist = intent.getBooleanExtra(EXIST, false)
            if (isExist) {
                //如果为真则退出本Activity,跳转到登录页
                LoginActivity.startActivity(this)
//                this.finish()
            }
        }
    }

    companion object {
        fun startActivity(context: Context, isExistsLogin: Boolean) {
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(EXIST, isExistsLogin)
            context.startActivity(intent)
        }
    }

}

object IntentParams {
    const val EXIST = "EXIST"//是否退出登录
}

