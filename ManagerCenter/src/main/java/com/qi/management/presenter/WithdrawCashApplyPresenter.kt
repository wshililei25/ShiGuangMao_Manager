package com.qi.management.presenter

import com.qi.management.presenter.view.WithdrawCashView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.WithdrawCashApply
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import javax.inject.Inject

class WithdrawCashApplyPresenter @Inject constructor() : BasePresenter<WithdrawCashView>() {

    @Inject
    lateinit var mOrderServiceImpl: ManagerServiceImpl

    fun getWithdrawCashApplyList(map: MutableMap<String, String>) {

        mOrderServiceImpl.getWithdrawCashApplyList(map).execute(object : BaseSubscriber<BasePagingResp<MutableList<WithdrawCashApply>>>(mView) {
            override fun onNext(t: BasePagingResp<MutableList<WithdrawCashApply>>) {
                mView.onGetWithdrawCashListResult(t)
            }
        }, mLifecycleProvider)

    }

}
