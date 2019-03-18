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

    fun putUserInfo(userInfo: UserInfo?) {
        AppPrefsUtils.putString(BaseConstant.KEY_SP_USER_ID, userInfo?.id ?: "")
        AppPrefsUtils.putString(BaseConstant.KEY_SP_TOKEN, userInfo?.token.toString() ?: "")
        AppPrefsUtils.putString(BaseConstant.KEY_SP_REAL_NAME, userInfo?.realName ?: "")
        AppPrefsUtils.putString(BaseConstant.KEY_SP_CARD, userInfo?.idCard ?: "")
        AppPrefsUtils.putString(BaseConstant.KEY_SP_FRONT, userInfo?.cardBefore ?: "")
        AppPrefsUtils.putString(BaseConstant.KEY_SP_REVERSE, userInfo?.cardAfter ?: "")
        AppPrefsUtils.putString(BaseConstant.KEY_SHOP_ID, userInfo?.shopId ?: "")
        AppPrefsUtils.putString(BaseConstant.KEY_SHOP_NAME, userInfo?.storeName ?: "")
        AppPrefsUtils.putString(BaseConstant.FOREGIFT, userInfo?.totalDeposit  ?: "0")
        AppPrefsUtils.putInt(BaseConstant.KEY_USER_TYPE, userInfo?.type ?: 0)
    }

    /**
     *  退出登录时清空存储
     */
    fun clearUserInfo() {
        AppPrefsUtils.remove(BaseConstant.KEY_SP_USER_ID)
        AppPrefsUtils.remove(BaseConstant.KEY_SP_TOKEN)
        AppPrefsUtils.remove(BaseConstant.KEY_SP_REAL_NAME)
        AppPrefsUtils.remove(BaseConstant.KEY_SP_CARD)
        AppPrefsUtils.remove(BaseConstant.KEY_SP_FRONT)
        AppPrefsUtils.remove(BaseConstant.KEY_SP_REVERSE)
        AppPrefsUtils.remove(BaseConstant.KEY_SHOP_ID)
        AppPrefsUtils.remove(BaseConstant.KEY_SHOP_NAME)
        AppPrefsUtils.remove(BaseConstant.FOREGIFT)
        AppPrefsUtils.remove(BaseConstant.KEY_USER_TYPE)
    }
}
