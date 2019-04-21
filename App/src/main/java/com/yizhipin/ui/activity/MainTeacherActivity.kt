package com.yizhipin.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
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
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener
import org.jetbrains.anko.toast
import java.util.*

/**
 * 管理端主页
 */
@Route(path = RouterPath.App.PATH_MAIN_TEACHER)
class MainTeacherActivity : BaseActivity() {

    private var mFragments: MutableList<Fragment>? = null
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
//        changeFragment(0)
    }

    private fun initFragment() {

        mFragments = ArrayList()
        mFragments!!.add(mHomeGridFragment)
        mFragments!!.add(mPersonnelFragment)
        mFragments!!.add(mChatListFragment)
        mFragments!!.add(mFinancialAffairsFragment)
        mFragments!!.add(mMeFragment)
        if (mHomeGridFragment != null) { //默认选中第一个
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.mFrameLayout, mHomeGridFragment)
            transaction.commit()
        }
    }

    private fun initBottomNav() {
        var navigationController = mBottomNavBar.material()
                .addItem(R.drawable.store, getString(com.yizhipin.base.R.string.navStore))
                .addItem(R.drawable.user1, getString(com.yizhipin.base.R.string.navPerson))
                .addItem(R.drawable.speech_bubble2, getString(com.yizhipin.base.R.string.navMsg))
                .addItem(R.drawable.invoice1, getString(com.yizhipin.base.R.string.titleFinance))
                .addItem(R.drawable.ic_mine_checked, getString(com.yizhipin.base.R.string.nav_bar_user))
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

    /*   private fun changeFragment(position: Int) {
           val manager = supportFragmentManager.beginTransaction()
           for (fragment in mStack) {
               manager.hide(fragment)
           }
           manager.show(mStack[position])
           manager.commit()
       }*/

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
