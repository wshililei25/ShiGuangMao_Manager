package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.enable
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.injection.component.DaggerUserComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.presenter.RegisterPresenter
import com.yizhipin.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


/**
 * Created by ${XiLei} on 2018/8/5.
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {

    private var mCurrentNum = 60
    private val TIME: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
    }

    private fun initView() {
        mBackIv.onClick(this)
        mSendCodeTv.onClick(this)
        mLoginBtn.onClick(this)
        mLoginBtn.enable(mMobileEt, { isBtnEnable() })
        mLoginBtn.enable(mCodeEt, { isBtnEnable() })
        mLoginBtn.enable(mPswEt, { isBtnEnable() })
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mBackIv -> finish()
            R.id.mSendCodeTv -> {
                if (mMobileEt.text.toString().isNullOrEmpty()) {
                    toast(R.string.input_mobile)
                    return
                }
                mCurrentNum = 60
                mSendCodeTv.postDelayed(mRefreshRunnable, TIME);
                var map = mutableMapOf<String, String>()
                map.put("mobile", mMobileEt.text.toString())
                mBasePresenter.getCode(map)
            }

            R.id.mLoginBtn -> {
                var map = mutableMapOf<String, String>()
                map.put("mobile", mMobileEt.text.toString())
                map.put("smsCode", mCodeEt.text.toString())
                map.put("password", mPswEt.text.toString())
                map.put("type", "1")
                mBasePresenter.register(map)
            }
        }
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

    /**
     * 注册成功
     */
    override fun onRegisterSuccess(result: UserInfo) {

        AppPrefsUtils.putString(BaseConstant.KEY_SP_REGISTER_USER_ID, result?.id ?: "")
        AppPrefsUtils.putString(BaseConstant.KEY_SP_TOKEN, result?.token.toString() ?: "")

        //由于后台做了环信注册，这里不需要再注册
        /*Thread(object : Runnable {
            override fun run() {
                try {
                    EMClient.getInstance().createAccount(mMobileEt.text.toString().trim(), mPswEt.text.toString().trim())
                    Log.d("XiLei", "环信注册成功")
                } catch (e: HyphenateException) {
                    e.printStackTrace()
                    Log.d("XiLei", "环信注册失败--- " + e.errorCode + "," + e.message)
                }
            }

        }).start()*/
        startActivity<UserInfoActivity>()
        finish()
    }

    private val mRefreshRunnable: Runnable = object : Runnable {
        override fun run() {
            mSendCodeTv.text = mCurrentNum.toString() + "s"

            if (mCurrentNum == 0) {
                mSendCodeTv.removeCallbacks(this)
                mSendCodeTv.isEnabled = true
                mSendCodeTv.text = "重发验证码"
            } else {
                mCurrentNum -= 1
                mSendCodeTv.isEnabled = false
                mSendCodeTv.postDelayed(this, TIME);
            }
        }
    }
}