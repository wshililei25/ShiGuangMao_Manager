package com.yizhipin.usercenter.utils

import com.alibaba.android.arouter.launcher.ARouter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.yizhipin.base.common.AppManager
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.provider.common.ProviderConstant
import com.yizhipin.provider.router.RouterPath

/**
 *本地存储用户相关信息
 */
object UserPrefsUtils {

    /**
     *  退出登录时，传入null,清空存储
     */
    fun putUserInfo(userInfo: UserInfo?) {
        AppPrefsUtils.putString(BaseConstant.KEY_SP_USER_ID, userInfo?.id ?: "")
        AppPrefsUtils.putString(BaseConstant.KEY_SP_TOKEN, userInfo?.token.toString() ?: "")
        AppPrefsUtils.putString(BaseConstant.KEY_SHOP_ID, userInfo?.shopId ?: "")
        AppPrefsUtils.putString(BaseConstant.KEY_SHOP_NAME, userInfo?.storeName ?: "")
        AppPrefsUtils.putInt(BaseConstant.KEY_USER_TYPE, userInfo?.type ?: 0)

        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_MOBILE, userInfo?.mobile ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_ICON, userInfo?.imgurl ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_SP_USER_NICKNAME, userInfo?.nickname ?: "")
        AppPrefsUtils.putString(ProviderConstant.KEY_AMOUNT, userInfo?.amount ?: "0")
        AppPrefsUtils.putString(ProviderConstant.KEY_USER_INFO, Gson().toJson(userInfo))
    }

    /**
     *  退出登录时清空存储
     */
    fun clearUserInfo() {
        AppPrefsUtils.remove(BaseConstant.KEY_SP_USER_ID)
        AppPrefsUtils.remove(BaseConstant.KEY_SP_TOKEN)
        AppPrefsUtils.remove(BaseConstant.KEY_SHOP_NAME)
        AppPrefsUtils.remove(BaseConstant.KEY_USER_TYPE)

        AppPrefsUtils.remove(ProviderConstant.KEY_SP_USER_MOBILE)
        AppPrefsUtils.remove(ProviderConstant.KEY_SP_USER_ICON)
        AppPrefsUtils.remove(ProviderConstant.KEY_SP_USER_NICKNAME)
        AppPrefsUtils.remove(ProviderConstant.KEY_AMOUNT)
        AppPrefsUtils.remove(ProviderConstant.KEY_USER_INFO)
    }

    fun getUserInfo(): UserInfo? {
        val userInfo = Gson().fromJson<UserInfo?>(AppPrefsUtils.getString(ProviderConstant.KEY_USER_INFO), object : TypeToken<UserInfo>() {}.type)
        if (userInfo == null) {
            ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
            AppManager.instance.finishAllExcludeCurrent()
        }
        return userInfo
    }

    fun getUserId(): String {
        return AppPrefsUtils.getString(BaseConstant.KEY_SP_USER_ID)
    }
}
