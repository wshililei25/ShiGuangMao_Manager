package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_teacher_apply_seccess.*

/**
 * Created by ${XiLei} on 2018/9/24.
 * 老师申请成功
 */

class TeacherApplySuccessActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_apply_seccess)

        initView()
    }

    private fun initView() {
        mHeaderBar.getBackIv().setVisible(false)
        mRebackTv.onClick(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mRebackTv -> {
                UserPrefsUtils.clearUserInfo()
                finish()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        UserPrefsUtils.clearUserInfo()
        finish()
    }
}