package com.yizhipin.base.widgets

import android.content.Context
import android.util.AttributeSet
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.yizhipin.base.R

/**
 * Created by ${XiLei} on 2018/8/19.
 * 主界面底部导航栏
 */
class BottomNavBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationBar(context, attrs, defStyleAttr) {

    init {

        val scheduleItem = BottomNavigationItem(
                R.drawable.ic_schedule_checked,
                resources.getString(R.string.navSchedule))
                .setInactiveIconResource(R.drawable.ic_dschedule_uncheck)
                .setActiveColorResource(R.color.yRed)
                .setInActiveColorResource(R.color.yBlackDeep)

        //消息
        val categoryItem = BottomNavigationItem(
                R.drawable.ic_msg_checked,
                resources.getString(R.string.navMsg))
                .setInactiveIconResource(R.drawable.ic_msg_unchecked)
                .setActiveColorResource(R.color.yRed)
                .setInActiveColorResource(R.color.yBlackDeep)
        //我的
        val userItem = BottomNavigationItem(
                R.drawable.ic_mine_checked,
                resources.getString(R.string.nav_bar_user))
                .setInactiveIconResource(R.drawable.ic_mine_unchecked)
                .setActiveColorResource(R.color.yRed)
                .setInActiveColorResource(R.color.yBlackDeep)

        setMode(BottomNavigationBar.MODE_FIXED)
        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
        setBarBackgroundColor(R.color.common_white)
                .addItem(scheduleItem)
                .addItem(categoryItem)
                .addItem(userItem)
                .setFirstSelectedPosition(0)
                .initialise()
    }

}