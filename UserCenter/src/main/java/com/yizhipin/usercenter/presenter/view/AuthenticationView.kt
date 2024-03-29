package com.yizhipin.usercenter.presenter.view

import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.mvp.view.BaseView

/**
 * Creator Qi
 * Date 2018/12/18
 */
interface AuthenticationView : BaseView {
    fun onGetOssSignSuccess(result: String)
    fun onGetOssSignFileSuccess(result: String)
    fun onGetOssAddressSuccess(result: OssAddress)
    fun onUpdateUserInfoSuccess(result: UserInfo)
}