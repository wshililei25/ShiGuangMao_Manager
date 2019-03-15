package com.qi.management.presenter.view

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.News
import com.yizhipin.base.mvp.view.BaseView

interface NewsView : BaseView {

    fun onGetNewsListResult(result: BasePagingResp<MutableList<News>>)
}
