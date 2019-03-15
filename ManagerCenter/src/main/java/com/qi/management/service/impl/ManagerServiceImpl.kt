package com.qi.management.service.impl

import com.qi.management.data.repository.ManagerRepository
import com.qi.management.service.ManagerService
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.*
import com.yizhipin.base.ext.convert
import com.yizhipin.base.ext.convertPaging
import com.yizhipin.goods.data.response.Complain
import com.yizhipin.usercenter.data.response.ManagerOrderDetails
import com.yizhipin.usercenter.data.response.StaffType
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class ManagerServiceImpl @Inject constructor() : ManagerService {

    @Inject
    lateinit var mRepository: ManagerRepository

    override fun getCameramanList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Teacher>>> {
        return mRepository.getCameramanList(map).convertPaging()
    }

    override fun getOrderList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<ManagerOrder>>> {
        return mRepository.getOrderList(map).convertPaging()
    }

    override fun getNewsList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<News>>> {
        return mRepository.getNewsList(map).convertPaging()
    }

    override fun getComplainList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<Complain>>> {
        return mRepository.getComplainList(map).convertPaging()
    }

    override fun getWithdrawCashApplyList(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<WithdrawCashApply>>> {
        return mRepository.getWithdrawCashApplyList(map).convertPaging()
    }

    override fun getStaffList(map: MutableMap<String, String>): Observable<MutableList<UserInfo>> {
        return mRepository.getStaffList(map).convert()
    }

    override fun getCustomerList(map: MutableMap<String, String>): Observable<MutableList<UserInfo>> {
        return mRepository.getCustomerList(map).convert()
    }

    override fun getStaffTypeList(): Observable<MutableList<StaffType>> {
        return mRepository.getStaffTypeList().convert()
    }

    override fun getStaffOrder(map: MutableMap<String, String>): Observable<MutableList<ManagerOrder>> {
        return mRepository.getStaffOrder(map).convert()
    }

    override fun getCameramanDetails(map: MutableMap<String, String>): Observable<Teacher> {
        return mRepository.getCameramanDetails(map).convert()
    }

    override fun addStaff(map: MutableMap<String, String>): Observable<UserInfo> {
        return mRepository.addStaff(map).convert()
    }
    override fun getFinancial(map: MutableMap<String, String>): Observable<Financial> {
        return mRepository.getFinancial(map).convert()
    }

    override fun getManagerOrderDetails(map: MutableMap<String, String>): Observable<ManagerOrderDetails> {
        return mRepository.getManagerOrderDetails(map).convert()
    }

    override fun getTeacherWorks(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<TeacherWorks>>> {
        return mRepository.getTeacherWorks(map).convertPaging()
    }

    override fun getTeacherEvaluate(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<TeacherWorks>>> {
        return mRepository.getTeacherEvaluate(map).convertPaging()
    }
}