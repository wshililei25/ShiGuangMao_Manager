package com.yizhipin.usercenter.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.yizhipin.base.ext.enable
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.injection.component.DaggerUserComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.presenter.ResetPwdPresenter
import com.yizhipin.usercenter.presenter.view.ResetPwdView
import com.yizhipin.usercenter.ui.activity.ResetPwdActivity.IntentParams.TITLE
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.toast


/**
 * Created by ${XiLei} on 2018/8/5.
 */
class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)
        initView()
        initdata()
    }

    private fun initdata() {
        val title = intent.getStringExtra(TITLE)
    }

    private fun initView() {
        delPhoneBtn.setOnClickListener(this::onDelPhoneClickListener)
        delPasswordBtn.setOnClickListener(this::onDelPasswordBtnClickListener)
        mSendCodeTv.onClick(this)
        loginBtn.onClick(this)
        loginBtn.enable(mMobileEt) { isBtnEnable() }
        loginBtn.enable(mCodeEt) { isBtnEnable() }
        loginBtn.enable(mPswEt) { isBtnEnable() }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mSendCodeTv -> {
                if (mMobileEt.text.toString().isNullOrEmpty()) {
                    toast(R.string.input_mobile)
                    return
                }
                var map = mutableMapOf<String, String>()
                map.put("mobile", mMobileEt.text.toString())
                mBasePresenter.getCode(map)
            }

            R.id.loginBtn -> {
                val map = mutableMapOf<String, String>()
                map["mobile"] = mMobileEt.text.toString()
                map["smsCode"] = mCodeEt.text.toString()
                map["password"] = mPswEt.text.toString()
                map["type"] = "0"
                mBasePresenter.resetPwd(map)
            }
        }
    }

    private fun onDelPhoneClickListener(view: View) {
        //删除账号
        mMobileEt.setText("")
    }

    private fun onDelPasswordBtnClickListener(view: View) {
        //删除账号
        mPswEt.setText("")
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mCodeEt.text.isNullOrEmpty().not() &&
                mPswEt.text.isNullOrEmpty().not()
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    /**
     * 获取验证码成功
     */
    override fun onGetCodeSuccess(result: Boolean) {
        toast(R.string.get_code_success)
    }

    override fun onResetPwdSuccess(result: Boolean) {
        finish()
    }

    companion object {
        fun startActivity(context: Context, title: String) {
            val intent = Intent(context, ResetPwdActivity::class.java)
            intent.putExtra(TITLE, title)
            context.startActivity(intent)
        }
    }

    object IntentParams {
        const val TITLE = "TITLE"
    }
}