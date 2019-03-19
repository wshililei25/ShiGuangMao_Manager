package com.qi.management.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.qi.management.R
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.goods.ui.adapter.ProfileVpAdapter
import kotlinx.android.synthetic.main.activity_tablayout.*

/**
 *  @author: XiLei  @date: 2019/3/19
 */
class ProfileActivity : BaseActivity() {

    @Autowired(name = BaseConstant.KEY_TEACHER_UID) //注解接收上个页面的传参
    @JvmField
    var mTeacherUid: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablayout)
        initView()
    }

    private fun initView() {
        mHeaderBar.getTiTleTv().text = "我的资料、作品"
        mTab.tabMode = TabLayout.MODE_FIXED
        var mVpAdapter = ProfileVpAdapter(supportFragmentManager, mTeacherUid)
        mVp.adapter = mVpAdapter
        mTab.setupWithViewPager(mVp)
    }
}