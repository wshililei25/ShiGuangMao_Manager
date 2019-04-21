package com.yizhipin.presenter.view

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.News
import com.yizhipin.base.mvp.view.BaseView

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface NewsView : BaseView {
    fun onGetNewsSuccess(result: BasePagingResp<MutableList<News>>)
}