package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.view.View
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.presenter.AuthenticationPresenter
import com.yizhipin.usercenter.presenter.view.AuthenticationView
import kotlinx.android.synthetic.main.activity_authentication.*
import org.jetbrains.anko.startActivity

/**
 * Creator Qi
 * Date 2018/12/18
 * 实名认证
 */
class AuthenticationActivity : BaseMvpActivity<AuthenticationPresenter>(), AuthenticationView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        initView()
    }

    override fun injectComponent() {
    }

    private fun initView() {
        mFrontCardPhoto.onClick(this)
        mBtn.onClick(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mFrontCardPhoto -> {

            }
            R.id.mBtn -> {
                startActivity<TeacherEnterDatumActivity>()
            }
        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(mes: String) {
    }
}