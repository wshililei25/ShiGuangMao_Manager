package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.ui.fragment.TeacherWorksFragment
import kotlinx.android.synthetic.main.activity_teacher_works.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/9/24.
 * 上传作品
 */

class TeacherWorksActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_works)

        initView()
    }

    private fun initView() {

        var transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mFramelayout, TeacherWorksFragment())
        transaction.commit()

        mHeaderBar.getRightTv().onClick {
            startActivity<TeacherApplySuccessActivity>()
            finish()
        }
    }

}