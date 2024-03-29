//package com.yizhipin.base.widgets
//
//import android.content.Context
//import android.util.AttributeSet
//import com.ashokvarma.bottomnavigation.BottomNavigationBar
//import com.ashokvarma.bottomnavigation.BottomNavigationItem
//import com.yizhipin.base.R
//
///**
// * Created by ${XiLei} on 2018/8/19.
// * 主界面底部导航栏
// */
//class BottomNavBarTeacher @JvmOverloads constructor(
//        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
//) : BottomNavigationBar(context, attrs, defStyleAttr) {
//
//    init {
//
//        //门店
//        val storeItem = BottomNavigationItem(
//                R.drawable.store,
//                resources.getString(R.string.navStore))
//                .setInactiveIconResource(R.drawable.store1)
//                .setActiveColorResource(R.color.yRed)
//                .setInActiveColorResource(R.color.yBlackDeep)
//        //人员
//        val personItem = BottomNavigationItem(R.drawable.user1, resources.getString(R.string.navPerson))
//                .setInactiveIconResource(R.drawable.user)
//                .setActiveColorResource(R.color.yRed)
//                .setInActiveColorResource(R.color.yBlackDeep)
//
//        //消息
//        val categoryItem = BottomNavigationItem(
//                R.drawable.speech_bubble2,
//                resources.getString(R.string.navMsg))
//                .setInactiveIconResource(R.drawable.speech_bubble)
//                .setActiveColorResource(R.color.yRed)
//                .setInActiveColorResource(R.color.yBlackDeep)
//        //财务
//        val financeItem = BottomNavigationItem(R.drawable.invoice1, resources.getString(R.string.titleFinance))
//                .setInactiveIconResource(R.drawable.invoice)
//                .setActiveColorResource(R.color.yRed)
//                .setInActiveColorResource(R.color.yBlackDeep)
//        //我的
//        val userItem = BottomNavigationItem(
//                R.drawable.ic_mine_checked,
//                resources.getString(R.string.nav_bar_user))
//                .setInactiveIconResource(R.drawable.ic_mine_unchecked)
//                .setActiveColorResource(R.color.yRed)
//                .setInActiveColorResource(R.color.yBlackDeep)
//
//        setMode(BottomNavigationBar.MODE_FIXED)
//        setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)
//        setBarBackgroundColor(R.color.common_white)
//                .addItem(storeItem)
//                .addItem(personItem)
//                .addItem(categoryItem)
//                .addItem(financeItem)
//                .addItem(userItem)
//                .setFirstSelectedPosition(0)
//                .initialise()
//    }
//
//}