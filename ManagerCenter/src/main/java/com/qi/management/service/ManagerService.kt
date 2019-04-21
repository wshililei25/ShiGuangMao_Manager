package com.qi.management.service

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.*
import com.yizhipin.goods.data.response.Complain
import com.yizhipin.usercenter.data.response.ManagerOrderDetails
import com.yizhipin.usercenter.data.response.StaffType
import io.reactivex.Observable


/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface ManagerService {

    fun getUserInfo(uid: String): Observable<UserInfo>
    fun getCameramanList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Teacher>>>
    fun getCameramanDetails(map: MutableMap<String, String>): Observable<Teacher>
    fun getTeacherWorks(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<TeacherWorks>>>
    fun getTeacherEvaluate(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<TeacherWorks>>>
    fun getStaffList(map: MutableMap<String, String>): Observable<MutableList<UserInfo>>
    fun getStaffOrder(map: MutableMap<String, String>): Observable<MutableList<ManagerOrder>>
    fun getStaffTypeList(): Observable<MutableList<StaffType>>
    fun addStaff(map: MutableMap<String, String>): Observable<UserInfo>
    fun getCustomerList(map: MutableMap<String, String>): Observable<MutableList<UserInfo>>
    fun getOrderList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<ManagerOrder>>>
    fun getManagerOrderDetails(map: MutableMap<String, String>): Observable<ManagerOrderDetails>
    fun getWithdrawCashApplyList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<WithdrawCashApply>>>
    fun getComplainList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Complain>>>
    fun getNewsList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<ManagerNews>>>
    fun getFinancial(map: MutableMap<String, String>): Observable<Financial>
    fun getStoreInfo(map: MutableMap<String, String>): Observable<Store>
    fun getTeacherDatum(map: MutableMap<String, String>): Observable<Teacher>
    fun getOssSign(map: MutableMap<String, String>): Observable<String>
    fun getOssSignFile(map: MutableMap<String, String>): Observable<String>
    fun getOssAddress(): Observable<OssAddress>
}
