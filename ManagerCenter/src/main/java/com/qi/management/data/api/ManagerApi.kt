package com.qi.management.data.api

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.*
import com.yizhipin.goods.data.response.Complain
import com.yizhipin.usercenter.data.api.Api
import com.yizhipin.usercenter.data.response.ManagerOrderDetails
import com.yizhipin.usercenter.data.response.StaffType
import io.reactivex.Observable
import retrofit2.http.*

interface ManagerApi {

    @GET("${Api.EDIT_USER_INFO}/{id}") //获取用户信息
    fun getUserInfo(@Path("id") id: String): Observable<BaseResp<UserInfo>>

    @GET(Api.CAMERAMAN_LIST)
    fun getCameramanList(@Query("currentPage") currentPage: String, @Query("storeId") storeId: String): Observable<BasePagingResp<MutableList<Teacher>>>

    @GET(Api.MANAGER_ORDER_LIST)
    fun getOrderList(@Query("currentPage") currentPage: String, @Query("storeId") storeId: String, @Query("status") status: String): Observable<BasePagingResp<MutableList<ManagerOrder>>>

    @GET(Api.MANAGER_NEWS_LIST)
    fun getNewsList(@Query("currentPage") currentPage: String, @Query("storeId") storeId: String, @Query("type") status: String): Observable<BasePagingResp<MutableList<ManagerNews>>>

    @GET(Api.COMPLAIN_LIST)
    fun getComplainList(@Query("currentPage") currentPage: String, @Query("storeId") storeId: String, @Query("questionType") status: String): Observable<BasePagingResp<MutableList<Complain>>>

    @GET(Api.WITHDRAW_CASH_APPLY_LIST)
    fun getWithdrawCashApplyList(@Query("currentPage") currentPage: String, @Query("storeId") storeId: String): Observable<BasePagingResp<MutableList<WithdrawCashApply>>>

    @GET(Api.STAFF_LIST)
    fun getStaffList(@Query("shopId") storeId: String): Observable<BaseResp<MutableList<UserInfo>>>

    @GET(Api.CUSTOMER_LIST)
    fun getCustomerList(@Query("shopId") storeId: String): Observable<BaseResp<MutableList<UserInfo>>>

    @GET(Api.STAFF_TYPE_LIST)
    fun getStaffTypeList(): Observable<BaseResp<MutableList<StaffType>>>

    @GET(Api.STAFF_ORDER_LIST)
    fun getStaffOrder(@Query("uid") storeId: String): Observable<BaseResp<MutableList<ManagerOrder>>>

    @GET(Api.TEACHER_WORKS_LIST)
    fun getTeacherWorks(@Query("currentPage") currentPage: String, @Query("uid") uid: String): Observable<BasePagingResp<MutableList<TeacherWorks>>>

    @GET(Api.TEACHER_EVALUATE_LIST)
    fun getTeacherEvaluate(@Query("currentPage") currentPage: String, @Query("teacherId") uid: String): Observable<BasePagingResp<MutableList<TeacherWorks>>>

    @GET(Api.FINANCIAL)
    fun getFinancial(@Query("storeId") storeId: String, @Query("queryTimeType") queryTimeType: String
                     , @Query("queryTime") queryTime: String, @Query("queryType") queryType: String): Observable<BaseResp<Financial>>

    @GET("${Api.CAMERAMAN_DETAILS}${"/{id}"}")
    fun getCameramanDetails(@Path("id") id: String): Observable<BaseResp<Teacher>>

    @POST(Api.STAFF_ADD)
    fun addStaff(): Observable<BaseResp<UserInfo>>

    @GET("${Api.MANAGER_ORDER_DETAILS}${"/{orderType}"}${"/{id}"}")
    fun getManagerOrderDetails(@Path("orderType") orderType: String, @Path("id") id: String): Observable<BaseResp<ManagerOrderDetails>>

    @GET("${Api.SHOP_DETAILS}${"/{id}"}")
    fun getStoreInfo(@Path("id") id: String): Observable<BaseResp<Store>>

    @GET("${Api.TEACHER_DATUM}/{uid}")
    fun getTeacherDatum(@Path("uid") uid: String): Observable<BaseResp<Teacher>>

    @GET(Api.OSS_SIGN)
    fun getOssSign(@Header("access-token") token: String, @Query("content") content: String): Observable<BaseResp<String>>

    @GET(Api.OSS_SIGN)
    fun getOssSignFile(@Header("access-token") token: String, @Query("content") content: String): Observable<BaseResp<String>>

    @GET(Api.IMAGE_ADDRESS)
    fun getOssAddress(): Observable<BaseResp<OssAddress>>
}
