package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ext.setVisible
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.usercenter.R
import kotlinx.android.synthetic.main.activity_teacher_apply_seccess.*
import org.jetbrains.anko.startActivity

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
            R.id.mRebackTv ->   ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
        }
    }

}