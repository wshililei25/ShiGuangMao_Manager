package com.qi.management.data.repository

import com.qi.management.data.api.ManagerApi
import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.net.RetrofitFactoryPost
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.*
import com.yizhipin.goods.data.response.Complain
import com.yizhipin.usercenter.data.response.ManagerOrderDetails
import com.yizhipin.usercenter.data.response.StaffType
import io.reactivex.Observable
import javax.inject.Inject

class ManagerRepository @Inject constructor() {

    fun getUserInfo(uid: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getUserInfo(uid)
    }

    fun getCameramanList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Teacher>>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getCameramanList(map["currentPage"]!!, map["storeId"]!!)
    }

    fun getOrderList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<ManagerOrder>>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getOrderList(map["currentPage"]!!, map["storeId"]!!, map["status"]!!)
    }

    fun getNewsList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<ManagerNews>>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getNewsList(map["currentPage"]!!, map["storeId"]!!, map["type"]!!)
    }

    fun getComplainList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Complain>>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getComplainList(map["currentPage"]!!, map["storeId"]!!, map["questionType"]!!)
    }

    fun getWithdrawCashApplyList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<WithdrawCashApply>>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getWithdrawCashApplyList(map["currentPage"]!!, map["storeId"]!!)
    }

    fun getStaffList(map: MutableMap<String, String>): Observable<BaseResp<MutableList<UserInfo>>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getStaffList(map["shopId"]!!)
    }
    fun getTeacherApplyList(map: MutableMap<String, String>): Observable<BaseResp<MutableList<Teacher>>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getTeacherApplyList(map["storeId"]!!)
    }
    fun approveTeacherApply(map: MutableMap<String, String>): Observable<BaseResp<Boolean>> {
        return RetrofitFactoryPost(map).create(ManagerApi::class.java).approveTeacherApply()
    }

    fun getCustomerList(map: MutableMap<String, String>): Observable<BaseResp<MutableList<UserInfo>>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getCustomerList(map["shopId"]!!)
    }

    fun getStaffTypeList(): Observable<BaseResp<MutableList<StaffType>>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getStaffTypeList()
    }

    fun getStaffOrder(map: MutableMap<String, String>): Observable<BaseResp<MutableList<ManagerOrder>>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getStaffOrder(map["uid"]!!)
    }

    fun getTeacherWorks(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<TeacherWorks>>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getTeacherWorks(map["currentPage"]!!, map["uid"]!!)
    }

    fun getTeacherEvaluate(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<TeacherWorks>>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getTeacherEvaluate(map["currentPage"]!!, map["teacherId"]!!)
    }

    fun getCameramanDetails(map: MutableMap<String, String>): Observable<BaseResp<Teacher>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getCameramanDetails(map["id"]!!)
    }

    fun addStaff(map: MutableMap<String, String>): Observable<BaseResp<UserInfo>> {
        return RetrofitFactoryPost(map).create(ManagerApi::class.java).addStaff()
    }

    fun getFinancial(map: MutableMap<String, String>): Observable<BaseResp<Financial>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getFinancial(map["storeId"]!!, map["queryTimeType"]!!, map["queryTime"]!!, map["queryType"]!!)
    }

    fun getManagerOrderDetails(map: MutableMap<String, String>): Observable<BaseResp<ManagerOrderDetails>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getManagerOrderDetails(map["orderType"]!!, map["id"]!!)
    }

    fun getStoreInfo(map: MutableMap<String, String>): Observable<BaseResp<Store>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getStoreInfo(map["id"]!!)
    }

    fun getTeacherDatum(map: MutableMap<String, String>): Observable<BaseResp<Teacher>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getTeacherDatum(map["uid"]!!)
    }

    fun getOssSign(map: MutableMap<String, String>): Observable<BaseResp<String>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getOssSign(map["access-token"]!!, map["content"]!!)
    }

    fun getOssSignFile(map: MutableMap<String, String>): Observable<BaseResp<String>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getOssSignFile(map["access-token"]!!, map["content"]!!)
    }

    fun getOssAddress(): Observable<BaseResp<OssAddress>> {
        return RetrofitFactoryGet().create(ManagerApi::class.java).getOssAddress()
    }
}
