package com.qi.management.presenter

import com.qi.management.presenter.view.ComplainView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.goods.data.response.Complain
import javax.inject.Inject

class ComplainPresenter @Inject constructor() : BasePresenter<ComplainView>() {

    @Inject
    lateinit var mOrderServiceImpl: ManagerServiceImpl

    fun getComplainList(map: MutableMap<String, String>) {

        mOrderServiceImpl.getComplainList(map).execute(object : BaseSubscriber<BasePagingResp<MutableList<Complain>>>(mView) {
            override fun onNext(t: BasePagingResp<MutableList<Complain>>) {
                mView.onGetComplainListResult(t)
            }
        }, mLifecycleProvider)

    }

}
