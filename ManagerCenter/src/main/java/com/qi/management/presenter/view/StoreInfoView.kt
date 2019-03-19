package com.qi.management.presenter.view

import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.Store
import com.yizhipin.base.mvp.view.BaseView

interface StoreInfoView : BaseView {

    fun onGetStoreInfoResult(result: Store)
    fun onGetOssSignSuccess(result: String)
    fun onGetOssSignFileSuccess(result: String)
    fun onGetOssAddressSuccess(result: OssAddress)
}
