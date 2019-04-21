package com.yizhipin.usercenter.service.impl

import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.*
import com.yizhipin.base.ext.convert
import com.yizhipin.base.ext.convertBoolean
import com.yizhipin.usercenter.bean.WorkStatusBean
import com.yizhipin.usercenter.data.repository.UserRepository
import com.yizhipin.usercenter.service.UserService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class UserServiceImpl @Inject constructor() : UserService {
    @Inject
    lateinit var mRepository: UserRepository

    /**
     * 获取用户上班状态
     */
    override fun getUserWorkStatusList(uid: String): Observable<BaseResp<List<WorkStatusBean>>> {
        return mRepository.getWorkStatusList(uid)
    }

    /**
     * 更新用户上班状态
     */
    override fun postUserWorkStatus(map: MutableMap<String, String>): Observable<WorkStatusBean> {
        return mRepository.postUserWorkStatus(map).convert()
    }

    override fun getCode(map: MutableMap<String, String>): Observable<Boolean> {

        return mRepository.getCode(map)
                .convertBoolean()
    }

    override fun resetPwd(map: MutableMap<String, String>): Observable<Boolean> {

        return mRepository.resetPwd(map)
                .convertBoolean()
    }

    override fun register(map: MutableMap<String, String>): Observable<UserInfo> {

        return mRepository.register(map)
                .convert()
    }

    override fun login(map: MutableMap<String, String>): Observable<UserInfo> {

        return mRepository.login(map)
                .convert()
    }

    /**
     * 获取用户信息
     */
    override fun getUserInfo(uid: String): Observable<UserInfo> {
        return mRepository.getUserInfo(uid).convert()
    }

    /**
     * 编辑用户资料
     */
    override fun editUserInfo(map: MutableMap<String, String>): Observable<UserInfo> {
        return mRepository.editUserInfo(map)
                .convert()
    }

    override fun getCartCount(map: MutableMap<String, String>): Observable<Int> {
        return mRepository.getCartCount(map).convert()
    }

    /**
     * 绑定手机号
     */
    override fun bindMobile(map: MutableMap<String, String>): Observable<Boolean> {

        return mRepository.bindMobile(map)
                .convertBoolean()
    }

    override fun setPayPwd(map: MutableMap<String, String>): Observable<Boolean> {

        return mRepository.setPayPwd(map)
                .convertBoolean()
    }

    override fun updatePayPwd(map: MutableMap<String, String>): Observable<Boolean> {

        return mRepository.updatePayPwd(map)
                .convertBoolean()
    }

    override fun resetPayPwd(map: MutableMap<String, String>): Observable<Boolean> {

        return mRepository.resetPayPwd(map)
                .convertBoolean()
    }

    override fun getShopList(): Observable<MutableList<Store>> {
        return mRepository.getShopList().convert()
    }

    override fun applyEnterDatum(map: MutableMap<String, String>): Observable<Teacher> {
        return mRepository.applyEnterDatum(map).convert()
    }

    override fun loadFeeRecordList(map: MutableMap<String, String>): Observable<MutableList<FeeRecord>> {
        return mRepository.loadFeeRecordList(map).convert()
    }

    override fun getInvitationList(map: MutableMap<String, String>): Observable<MutableList<UserInfo>> {
        return mRepository.getInvitationList(map)
                .convert()
    }

    override fun addInvitation(map: MutableMap<String, String>): Observable<UserInfo> {
        return mRepository.addInvitation(map)
                .convert()
    }

    override fun getOssSign(map: MutableMap<String, String>): Observable<String> {
        return mRepository.getOssSign(map).convert()
    }

    override fun getOssSignFile(map: MutableMap<String, String>): Observable<String> {
        return mRepository.getOssSignFile(map).convert()
    }

    override fun getOssAddress(): Observable<OssAddress> {
        return mRepository.getOssAddress().convert()
    }

    override fun updateUserInfo(map: MutableMap<String, String>): Observable<UserInfo> {
        return mRepository.updateUserInfo(map).convert()
    }

    override fun addWork(map: MutableMap<String, String>): Observable<Works> {
        return mRepository.addWork(map).convert()
    }
    override fun getWorksList(map: MutableMap<String, String>): Observable<MutableList<Works>> {
        return mRepository.getWorksList(map).convert()
    }
    override fun getUnreadNewCount(map: MutableMap<String, String>): Observable<Int> {
        return mRepository.getUnreadNewCount(map)
                .convert()
    }

}