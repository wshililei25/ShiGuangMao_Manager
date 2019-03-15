package com.yizhipin.usercenter.presenter

import com.alibaba.android.arouter.launcher.ARouter
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.FeeRecord
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.base.rx.CodeHandlerSubscriber
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.usercenter.bean.WorkStatusBean
import com.yizhipin.usercenter.presenter.view.UserInfoView
import com.yizhipin.usercenter.service.impl.UserServiceImpl
import com.yizhipin.usercenter.utils.UserPrefsUtils
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoView>() {

    @Inject
    lateinit var mServiceImpl: UserServiceImpl

    /**
     * 获取用户信息
     */
    fun getUserInfo(map: MutableMap<String, String>) {
        mView.showLoading()
        mServiceImpl.getUserInfo(AppPrefsUtils.getString(BaseConstant.KEY_SP_USER_ID)).execute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                mView.getUserResult(t)
            }
        }, mLifecycleProvider)
    }

    fun getUserInfo() {
        val userInfo = UserPrefsUtils.getUserInfo()
        if (userInfo != null)
            mView.getUserResult(userInfo)
        else {
            ARouter.getInstance().build(RouterPath.UserCenter.PATH_LOGIN).navigation()
//            mView.getActivity()?.finish()
        }
    }

    /**
     * 编辑用户资料
     */
    fun editUserInfo(map: MutableMap<String, String>) {
        mView.showLoading()
        mServiceImpl.editUserInfo(map).execute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                mView.onEditUserResult(t)
            }
        }, mLifecycleProvider)
    }

    /**
     * 获取购物车数量
     */
    fun getCartCount(map: MutableMap<String, String>) {

        mServiceImpl.getCartCount(map).execute(object : BaseSubscriber<Int>(mView) {
            override fun onNext(t: Int) {
                mView.onGetCartSuccess(t)
            }
        }, mLifecycleProvider)
    }

    /**
     * 工作状态
     */
    fun getWorkStatusList() {
        mServiceImpl.getUserWorkStatusList(UserPrefsUtils.getUserId()).execute(object : CodeHandlerSubscriber<List<WorkStatusBean>>(mView) {
            override fun onSucceed(data: List<WorkStatusBean>) {
                if (data.isNotEmpty())
                    mView.showWorkStatus(data[0])
            }
        }, mLifecycleProvider)
    }

    /***上下班打卡*/
    fun postWorkStatus() {
        if (UserPrefsUtils.getUserInfo() == null)
            return
        mServiceImpl.postUserWorkStatus(UserPrefsUtils.getUserId(), !UserPrefsUtils.getUserInfo()!!.work).execute(object : CodeHandlerSubscriber<WorkStatusBean>(mView) {
            override fun onSucceed(data: WorkStatusBean) {
                mView.showWorkStatus(data)
            }
        }, mLifecycleProvider)
    }

    fun loadFeeRecordList(map: MutableMap<String, String>) {
        mView.showLoading()
        mServiceImpl.loadFeeRecordList(map).execute(object : BaseSubscriber<MutableList<FeeRecord>>(mView) {
            override fun onNext(t: MutableList<FeeRecord>) {
                mView.getFeeRecordListSuccess(t)
            }
        }, mLifecycleProvider)
    }


    fun getOssSign(map: MutableMap<String, String>) {

        mServiceImpl.getOssSign(map).execute(object : BaseSubscriber<String>(mView) {
            override fun onNext(t: String) {
                mView.onGetOssSignSuccess(t)
            }
        }, mLifecycleProvider)
    }

    fun getOssSignFile(map: MutableMap<String, String>) {

        mServiceImpl.getOssSignFile(map).execute(object : BaseSubscriber<String>(mView) {
            override fun onNext(t: String) {
                mView.onGetOssSignFileSuccess(t)
            }
        }, mLifecycleProvider)
    }

    fun getOssAddress() {
        mServiceImpl.getOssAddress().execute(object : BaseSubscriber<OssAddress>(mView) {
            override fun onNext(t: OssAddress) {
                mView.onGetOssAddressSuccess(t)
            }
        }, mLifecycleProvider)

    }
}

