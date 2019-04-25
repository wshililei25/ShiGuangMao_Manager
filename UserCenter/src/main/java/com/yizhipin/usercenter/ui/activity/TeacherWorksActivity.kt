package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.ui.fragment.TeacherWorksFragment
import kotlinx.android.synthetic.main.activity_teacher_works.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by ${XiLei} on 2018/9/24.
 * 上传作品
 */

class TeacherWorksActivity : BaseActivity() {

    private lateinit var mTeacherWorksFragment: TeacherWorksFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_works)

        initView()
    }

    private fun initView() {
        mTeacherWorksFragment = TeacherWorksFragment()
        var transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mFramelayout, mTeacherWorksFragment)
        transaction.commit()

        mHeaderBar.getRightTv().onClick {

            if (mTeacherWorksFragment.mAdapter.dataList.size <= 0) {
                toast("请添加作品")
                return@onClick
            }
            startActivity<TeacherApplySuccessActivity>()
            finish()
        }
    }

}