package com.qi.management.presenter.view

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.mvp.view.BaseView
import com.yizhipin.goods.data.response.Complain

interface ComplainView : BaseView {

    fun onGetComplainListResult(result: BasePagingResp<MutableList<Complain>>)
}
