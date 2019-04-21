package com.yizhipin.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.yizhipin.R
import com.yizhipin.base.common.AppManager
import com.yizhipin.base.event.HomeIntentEvent
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.teacher.schedule.ui.fragment.ScheduleFragment
import com.yizhipin.ui.fragment.ChatListFragment
import com.yizhipin.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_main.*
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener
import org.jetbrains.anko.toast
import java.util.*

/**
 * 老师端主页
 */
@Route(path = RouterPath.App.PATH_MAIN)
class MainActivity : BaseActivity() {

    private var mFragments: MutableList<Fragment>? = null
    private val mScheduleFragment by lazy { ScheduleFragment() }
    private val mChatListFragment by lazy { ChatListFragment() }
    private val mMeFragment by lazy { MeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        initBottomNav()
//        changeFragment(0)
        initObserve()
    }

    private fun initFragment() {


        mFragments = ArrayList()
        mFragments!!.add(mScheduleFragment)
        mFragments!!.add(mChatListFragment)
        mFragments!!.add(mMeFragment)
        if (mScheduleFragment != null) { //默认选中第一个
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.mFrameLayout, mScheduleFragment)
            transaction.commit()
        }
    }

    private fun initBottomNav() {
        var navigationController = mBottomNavBar.material()
                .addItem(R.drawable.ic_schedule_checked, getString(R.string.navSchedule))
                .addItem(R.drawable.speech_bubble2, getString(R.string.navMsg))
                .addItem(R.drawable.ic_mine_checked, getString(R.string.nav_bar_user))
                .setDefaultColor(ContextCompat.getColor(this, R.color.yGray))
                .build()

        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(object : OnTabItemSelectedListener {
            override fun onSelected(index: Int, old: Int) {
                val currentFragment = mFragments!!.get(index)
                if (currentFragment != null) {
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.mFrameLayout, currentFragment!!)
                    transaction.commit()
                }
            }

            override fun onRepeat(index: Int) {}
        })
    }

    /* private fun changeFragment(position: Int) {
         val manager = supportFragmentManager.beginTransaction()
         for (fragment in mStack) {
             manager.hide(fragment)
         }
         manager.show(mStack[position])
         manager.commit()
     }*/

    private fun initObserve() {
        Bus.observe<HomeIntentEvent>()
                .subscribe { t: HomeIntentEvent ->
                    run {

                        if (t.position == 3) {
//                            changeFragment(2)
//                            mBottomNavBar.selectTab(2)
                        } else {
//                            changeFragment(1)
//                            mBottomNavBar.selectTab(1)
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

}
