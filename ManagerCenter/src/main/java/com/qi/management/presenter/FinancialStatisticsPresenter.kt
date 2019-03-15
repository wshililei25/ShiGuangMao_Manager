package com.qi.management.presenter

import com.qi.management.presenter.view.FinancialStatisticsView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.data.response.Financial
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import javax.inject.Inject

class FinancialStatisticsPresenter @Inject constructor() : BasePresenter<FinancialStatisticsView>() {

    @Inject
    lateinit var mOrderServiceImpl: ManagerServiceImpl

    fun getFinancial(map: MutableMap<String, String>) {

        mOrderServiceImpl.getFinancial(map).execute(object : BaseSubscriber<Financial>(mView) {
            override fun onNext(t: Financial) {
                mView.onGetFinancialResult(t)
            }
        }, mLifecycleProvider)

    }

}
