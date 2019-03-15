package com.qi.management.presenter

import com.qi.management.presenter.view.NewsView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.News
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import javax.inject.Inject

class NewsPresenter @Inject constructor() : BasePresenter<NewsView>() {

    @Inject
    lateinit var mOrderServiceImpl: ManagerServiceImpl

    fun getNewsList(map: MutableMap<String, String>) {

        mOrderServiceImpl.getNewsList(map).execute(object : BaseSubscriber<BasePagingResp<MutableList<News>>>(mView) {
            override fun onNext(t: BasePagingResp<MutableList<News>>) {
                mView.onGetNewsListResult(t)
            }
        }, mLifecycleProvider)

    }

}
