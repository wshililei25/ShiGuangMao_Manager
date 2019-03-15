package com.qi.management.presenter.view

import com.yizhipin.base.data.response.Financial
import com.yizhipin.base.mvp.view.BaseView

interface FinancialStatisticsView : BaseView {

    fun onGetFinancialResult(result: Financial)
}
