package com.yizhipin.usercenter.presenter.view

import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.Works
import com.yizhipin.base.mvp.view.BaseView

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface TeacherWorkView : BaseView {
    fun onGetOssSignSuccess(result: String)
    fun onGetOssSignFileSuccess(result: String)
    fun onGetOssAddressSuccess(result: OssAddress)
    fun onAddWorkSuccess(result: Works)
    fun onGetWorkListSuccess(result: MutableList<Works>)
}