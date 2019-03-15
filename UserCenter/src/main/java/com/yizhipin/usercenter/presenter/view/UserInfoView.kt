package com.yizhipin.usercenter.presenter.view

import com.yizhipin.base.data.response.FeeRecord
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.mvp.view.BaseView
import com.yizhipin.usercenter.bean.WorkStatusBean

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface UserInfoView : BaseView {
    fun getUserResult(result: UserInfo)
    fun onEditUserResult(result: UserInfo)
    fun onGetCartSuccess(result: Int)
    fun showWorkStatus(workStatusBean: WorkStatusBean)
    fun getFeeRecordListSuccess(result: MutableList<FeeRecord>)
    fun onGetOssSignSuccess(result: String)
    fun onGetOssSignFileSuccess(result: String)
    fun onGetOssAddressSuccess(result: OssAddress)
}