package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.enable
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.injection.component.DaggerUserComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.presenter.LoginPresenter
import com.yizhipin.usercenter.presenter.view.LoginView
import com.yizhipin.usercenter.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/8/5.
 * 登录
 */
@Route(path = RouterPath.UserCenter.PATH_LOGIN)
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {
    /**类型(0个人,1老师,2管理人员)*/
    private var roleType: Int = 1
    private var mUserInfo: UserInfo? = null

    override fun onCreateView(): Int {
        return R.layout.activity_login
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        mBackIv.onClick(this)
        mRegistBtn.onClick(this)
        mLoginBtn.onClick(this)
        mRightTv.onClick(this)
        mLoginBtn.enable(mMobileEt) { isBtnEnable() }
        mLoginBtn.enable(mPswEt) { isBtnEnable() }
        roleCheckBox.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                //员工
                roleType = 2
                mRegistBtn.visibility = GONE
            } else {
                //老师
                roleType = 1
                mRegistBtn.visibility = VISIBLE
            }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mBasePresenter.getOssAddress()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mBackIv -> finish()
            R.id.mRegistBtn -> startActivity<RegisterActivity>()
            R.id.mRightTv -> {
                ResetPwdActivity.startActivity(this, resources.getString(R.string.forget_pwd))
            }

            R.id.mLoginBtn -> {
                val map = mutableMapOf<String, String>()
                map["mobile"] = mMobileEt.text.toString()
                map["password"] = mPswEt.text.toString()
                map["deviceToken"] = ""
                map["deviceType"] = "android"
                map["type"] = "$roleType"
                mBasePresenter.login(map)
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mPswEt.text.isNullOrEmpty().not()
    }

    /**
     * 登录成功
     */
    override fun onLoginSuccess(result: UserInfo) {
        mUserInfo = result
        loginHuanXin(result.id)
        UserPrefsUtils.putUserInfo(result)

        when (result.type) {
            1 -> { //老师

                if (mUserInfo!!.realName.isNullOrBlank()) {
                    startActivity<AuthenticationActivity>()
                } else if (mUserInfo!!.storeName.isNullOrBlank()) {
                    startActivity<TeacherEnterDatumActivity>()
                } else {
                    val map = mutableMapOf<String, String>()
                    map["uid"] = result.id
                    mBasePresenter.getTeacherIofo(map)
                }

            }
            //管理
            2 -> ARouter.getInstance().build(RouterPath.App.PATH_MAIN_TEACHER).navigation()
        }
//        finish()
    }

    /**
     * 获取老师资料成功
     */
    override fun onGetTeahcerInfoSuccess(result: Teacher) {

        mUserInfo?.let {
            //            AppPrefsUtils.putString(BaseConstant.KEY_TEACEHR_STATUS,result.status)
            /* if (mUserInfo!!.realName.isNullOrBlank()) {
                 startActivity<AuthenticationActivity>()
             } else if (mUserInfo!!.storeName.isNullOrBlank()) {
                 startActivity<TeacherEnterDatumActivity>()
             } else */

            if (result.works == null || result.works.size <= 0) {
                startActivity<TeacherWorksActivity>()
            } else if (result.status == "0") { //未审核
                startActivity<TeacherApplySuccessActivity>()
            } else if (mUserInfo!!.totalDeposit.toDouble() <= 0) {
                startActivity<CashPledgeActivity>()
            } else {
                ARouter.getInstance().build(RouterPath.App.PATH_MAIN).navigation()
            }
        }

    }

    private fun loginHuanXin(uid: String) {
        Log.d("XiLei", "环信登录---uid====" + uid)
        EMClient.getInstance().login(uid, mPswEt.text.toString().trim(), object : EMCallBack {

            override fun onSuccess() {
                Log.d("XiLei", "环信登录成功")
            }

            override fun onProgress(progress: Int, status: String) {
            }

            override fun onError(code: Int, error: String) {
                Log.d("XiLei", "环信登录失败" + code + "," + error)
//                runOnUiThread { Toast.makeText(applicationContext, "login failed", 0).show() }
            }
        })
    }
}