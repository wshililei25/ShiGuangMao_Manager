package com.yizhipin.teacher.mine.setting

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import com.yizhipin.R
import com.yizhipin.base.common.AppManager
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.provider.common.ProviderConstant
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.teacher.mine.setting.mvp.SystemSettingContract
import com.yizhipin.teacher.mine.setting.mvp.SystemSettingPresenterImpl
import com.yizhipin.teacher.schedule.ui.activity.VersionInfoActivity
import com.yizhipin.usercenter.ui.activity.LoginActivity
import com.yizhipin.usercenter.ui.activity.ResetPwdActivity
import com.yizhipin.usercenter.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_systemsetting.*
import org.jetbrains.anko.startActivity

/**
 * Creator Qi
 * Date 2018/12/30
 * <p>系统设置</p>
 */
@Route(path = RouterPath.UserCenter.SYSTEM_SETTING)
class SystemSettingActivity : BaseMvpActivity<SystemSettingPresenterImpl>(), SystemSettingContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_systemsetting)
        passwordBtn.setOnClickListener(this::onPasswordBtnClickListener)
        versionBtn.setOnClickListener(this::onVersionBtnClickListener)
        loginOutBtn.setOnClickListener(this::onLoginOutBtnClickListener)
        pushToggle.isChecked = AppPrefsUtils.getBoolean(ProviderConstant.KEY_IS_PUSH)
        pushToggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppPrefsUtils.putBoolean(ProviderConstant.KEY_IS_PUSH, true)
            } else {
                AppPrefsUtils.putBoolean(ProviderConstant.KEY_IS_PUSH, false)
            }
        }
    }

    override fun injectComponent() {

    }

    private fun onPasswordBtnClickListener(view: View) {
        //修改密码
        ResetPwdActivity.startActivity(view.context, resources.getString(R.string.titlePasswordModification))
    }

    private fun onVersionBtnClickListener(view: View) {
        //进入版本信息页面
        VersionInfoActivity.startActivity(view.context)
    }

    private fun onLoginOutBtnClickListener(view: View) {
        //退出登录
//        MainActivity.startActivity(view.context, true)

        EMClient.getInstance().logout(false, object : EMCallBack {
            override fun onSuccess() {
                Log.d("XiLei", "环信退出登录成功")
            }

            override fun onProgress(p0: Int, p1: String?) {
            }

            override fun onError(p0: Int, p1: String?) {
                Log.d("XiLei", "环信退出登录失败---" + p0 + "," + p1)
            }

        })
        UserPrefsUtils.clearUserInfo()
        AppManager.instance.finishAllActivity()
        startActivity<LoginActivity>()
        finish()
    }

    companion object {
        fun startActivity(fragment: Fragment) {
            val intent = Intent(fragment.context, SystemSettingActivity::class.java)
            fragment.startActivity(intent)
        }
    }
}