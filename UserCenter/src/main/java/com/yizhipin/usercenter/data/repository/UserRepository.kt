package com.yizhipin.usercenter.data.repository

import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.net.RetrofitFactoryPost
import com.yizhipin.base.data.net.RetrofitFactoryPut
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.*
import com.yizhipin.base.mvp.model.BaseModel
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.usercenter.bean.WorkStatusBean
import com.yizhipin.usercenter.data.api.UserApi
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/27.
 */
class UserRepository @Inject constructor() : BaseModel {
    override fun start() {

    }

    override fun destroy() {
    }

    fun getCode(map: MutableMap<String, String>): Observable<BaseResp<Boolean>> {

        return RetrofitFactoryGet().create(UserApi::class.java)
                .getCode(map["mobile"]!!)
    }

    fun resetPwd(map: MutableMap<String, String>): Observable<BaseResp<Boolean>> {

        return RetrofitFactoryPost(map).create(UserApi::class.java)
                .resetPwd()
    }

    fun register(map: MutableMap<String, String>): Observable<BaseResp<UserInfo>> {

        return RetrofitFactoryPost(map).create(UserApi::class.java)
                .register()
    }

    fun login(map: MutableMap<String, String>): Observable<BaseResp<UserInfo>> {

        return RetrofitFactoryPost(map).create(UserApi::class.java)
                .login()
    }

    /**
     * 获取用户信息
     */
    fun getUserInfo(uid: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactoryGet().create(UserApi::class.java).getUserInfo(uid)
    }

    /**
     * 获取工作状态
     */
    fun getWorkStatusList(uid: String): Observable<BaseResp<List<WorkStatusBean>>> {
        return RetrofitFactoryGet().create(UserApi::class.java)
                .getUserWorkStatusList(uid)
    }

    /**
     * 编辑用户信息
     */
    fun editUserInfo(map: MutableMap<String, String>): Observable<BaseResp<UserInfo>> {
        return RetrofitFactoryPut(map).create(UserApi::class.java)
                .editUserInfo(AppPrefsUtils.getString(BaseConstant.KEY_SP_REGISTER_USER_ID))
    }

    fun getCartCount(map: MutableMap<String, String>): Observable<BaseResp<Int>> {
        return RetrofitFactoryGet().create(UserApi::class.java).getCartCount(map["uid"]!!)
    }

    /**
     * 绑定手机号
     */
    fun bindMobile(map: MutableMap<String, String>): Observable<BaseResp<Boolean>> {
        return RetrofitFactoryPut(map).create(UserApi::class.java)
                .bindMobile(AppPrefsUtils.getString(BaseConstant.KEY_SP_USER_ID))
    }

    fun setPayPwd(map: MutableMap<String, String>): Observable<BaseResp<Boolean>> {
        return RetrofitFactoryPost(map).create(UserApi::class.java)
                .setPayPwd()
    }

    fun updatePayPwd(map: MutableMap<String, String>): Observable<BaseResp<Boolean>> {
        return RetrofitFactoryPost(map).create(UserApi::class.java)
                .updatePayPwd()
    }

    fun resetPayPwd(map: MutableMap<String, String>): Observable<BaseResp<Boolean>> {
        return RetrofitFactoryPost(map).create(UserApi::class.java)
                .resetPayPwd()
    }

    fun postUserWorkStatus(map: MutableMap<String, String>): Observable<BaseResp<WorkStatusBean>> {
        return RetrofitFactoryPost(map).create(UserApi::class.java).postUserWorkStatus()
    }

    fun getShopList(): Observable<BaseResp<MutableList<Store>>> {
        return RetrofitFactoryGet().create(UserApi::class.java).getShopList()
    }

    fun applyEnterDatum(map: MutableMap<String, String>): Observable<BaseResp<Teacher>> {
        return RetrofitFactoryPost(map).create(UserApi::class.java).applyEnterDatum()
    }

    fun loadFeeRecordList(map: MutableMap<String, String>): Observable<BaseResp<MutableList<FeeRecord>>> {
        return RetrofitFactoryGet().create(UserApi::class.java)
                .loadFeeRecordList(map["uid"]!!)
    }

    fun getInvitationList(map: MutableMap<String, String>): Observable<BaseResp<MutableList<UserInfo>>> {
        return RetrofitFactoryGet().create(UserApi::class.java)
                .getInvitationList(map["uid"]!!)
    }

    fun addInvitation(map: MutableMap<String, String>): Observable<BaseResp<UserInfo>> {
        return RetrofitFactoryGet().create(UserApi::class.java)
                .addInvitation(map["uid"]!!, map["requestCode"]!!)
    }

    fun getOssSign(map: MutableMap<String, String>): Observable<BaseResp<String>> {
        return RetrofitFactoryGet().create(UserApi::class.java).getOssSign(map["access-token"]!!, map["content"]!!)
    }

    fun getOssSignFile(map: MutableMap<String, String>): Observable<BaseResp<String>> {
        return RetrofitFactoryGet().create(UserApi::class.java).getOssSignFile(map["access-token"]!!,map["content"]!!)
    }

    fun getOssAddress(): Observable<BaseResp<OssAddress>> {
        return RetrofitFactoryGet().create(UserApi::class.java).getOssAddress()
    }
    fun updateUserInfo(map: MutableMap<String, String>): Observable<BaseResp<UserInfo>> {
        return RetrofitFactoryPut(map).create(UserApi::class.java).updateUserInfo(map["id"]!!)
    }
    fun addWork(map: MutableMap<String, String>): Observable<BaseResp<Works>> {
        return RetrofitFactoryPost(map).create(UserApi::class.java).addWork()
    }
    fun getWorksList(map: MutableMap<String, String>): Observable<BaseResp<MutableList<Works>>> {
        return RetrofitFactoryGet().create(UserApi::class.java).getWorksList(map["uid"]!!)
    }
    fun getUnreadNewCount(map: MutableMap<String, String>): Observable<BaseResp<Int>> {
        return RetrofitFactoryGet().create(UserApi::class.java)
                .getUnreadNewCount(map["uid"]!!)
    }
}