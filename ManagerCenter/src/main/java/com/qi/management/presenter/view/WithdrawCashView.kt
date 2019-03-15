package com.qi.management.presenter.view

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.WithdrawCashApply
import com.yizhipin.base.mvp.view.BaseView

interface WithdrawCashView : BaseView {

    fun onGetWithdrawCashListResult(result: BasePagingResp<MutableList<WithdrawCashApply>>)
}
