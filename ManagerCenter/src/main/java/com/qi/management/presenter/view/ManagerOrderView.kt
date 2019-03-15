package com.qi.management.presenter.view

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.ManagerOrder
import com.yizhipin.base.mvp.view.BaseView

interface ManagerOrderView : BaseView {

    fun onGetOrderListResult(result: BasePagingResp<MutableList<ManagerOrder>>)
}
