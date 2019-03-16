package com.yizhipin.usercenter.data.api

import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.*
import com.yizhipin.usercenter.bean.WorkStatusBean
import com.yizhipin.usercenter.data.api.Api.Companion.WORK_STATUS_LIST
import com.yizhipin.usercenter.data.api.Api.Companion.WORK_STATUS_POST
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Created by ${XiLei} on 2018/7/27.
 */
interface UserApi {

    @POST(Api.GET_CODE) //获取验证码
    fun getCode(@Query("mobile") mobile: String): Observable<BaseResp<Boolean>>

    @POST(Api.RESET_PWD) //重置密码
    fun resetPwd(): Observable<BaseResp<Boolean>>

    @POST(Api.REGISTER) //注册
    fun register(): Observable<BaseResp<UserInfo>>

    @POST(Api.LOGIN) //登录
    fun login(): Observable<BaseResp<UserInfo>>

    @GET("${Api.EDIT_USER_INFO}/{id}") //获取用户信息
    fun getUserInfo(@Path("id") id: String): Observable<BaseResp<UserInfo>>

    //    @PUT(Api.EDIT_USER_INFO.plus("/{id}")) //编辑用户信息
    @PUT("${Api.EDIT_USER_INFO}${"/{id}"}") //编辑用户信息
    fun editUserInfo(@Path("id") id: String): Observable<BaseResp<UserInfo>>

    /**
     * 获取购物车数量
     */
    @PUT(Api.CART_COUNT)
    fun getCartCount(@Query("uid") uid: String): Observable<BaseResp<Int>>

    @PUT("${Api.BIND_MOBILE}${"/{id}"}") //绑定手机号
    fun bindMobile(@Path("id") id: String): Observable<BaseResp<Boolean>>

    /**
     * 设置支付密码
     */
    @POST(Api.SET_PAY_PWD)
    fun setPayPwd(): Observable<BaseResp<Boolean>>

    /**
     * 修改支付密码
     */
    @POST(Api.UPDATE_PAY_PWD)
    fun updatePayPwd(): Observable<BaseResp<Boolean>>

    /**
     * 重置支付密码
     */
    @POST(Api.RESET_PAY_PWD)
    fun resetPayPwd(): Observable<BaseResp<Boolean>>

    /**
     * 获取上下班状态
     */
    @GET(WORK_STATUS_LIST)
    fun getUserWorkStatusList(@Query("uid") uid: String): Observable<BaseResp<List<WorkStatusBean>>>

    /**
     * 更新工作状态
     */
    @POST(WORK_STATUS_POST)
    fun postUserWorkStatus(): Observable<BaseResp<WorkStatusBean>>

    @GET(Api.SHOP_LIST)
    fun getShopList(): Observable<BaseResp<MutableList<Store>>>

    @POST(Api.APPLY_ENTER_DATUM)
    fun applyEnterDatum(): Observable<BaseResp<Teacher>>

    @GET(Api.FEE_RECORD_LIST) //资金记录
    fun loadFeeRecordList(@Query("uid") uid: String): Observable<BaseResp<MutableList<FeeRecord>>>

    @GET(Api.INVITATION_LIST) //邀请人列表
    fun getInvitationList(@Query("uid") uid: String): Observable<BaseResp<MutableList<UserInfo>>>

    @GET(Api.INVITATION_ADD) //填写邀请码
    fun addInvitation(@Query("uid") uid: String, @Query("requestCode") requestCode: String): Observable<BaseResp<UserInfo>>

    @GET(Api.OSS_SIGN)
    fun getOssSign(@Header("access-token") token: String, @Query("content") content: String): Observable<BaseResp<String>>

    @GET(Api.OSS_SIGN)
    fun getOssSignFile(@Header("access-token") token: String, @Query("content") content: String): Observable<BaseResp<String>>

    @GET(Api.IMAGE_ADDRESS)
    fun getOssAddress(): Observable<BaseResp<OssAddress>>

    @PUT("${Api.EDIT_USER_INFO}${"/{id}"}")
    fun updateUserInfo(@Path("id") id: String): Observable<BaseResp<UserInfo>>

    @POST(Api.ADD_WORK)
    fun addWork(): Observable<BaseResp<Works>>

    @GET(Api.WORK_LIST)
    fun getWorksList(@Query("uid") uid: String): Observable<BaseResp<MutableList<Works>>>
}