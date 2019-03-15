package com.yizhipin.usercenter.presenter

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Store
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.usercenter.presenter.view.ShopView
import com.yizhipin.usercenter.service.impl.UserServiceImpl
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class ShopPresenter @Inject constructor() : BasePresenter<ShopView>() {

    @Inject
    lateinit var mServiceImpl: UserServiceImpl

    fun getShopList() {
        mView.showLoading()
        mServiceImpl.getShopList().execute(object : BaseSubscriber<MutableList<Store>>(mView) {
            override fun onNext(t: MutableList<Store>) {
                mView.onGetShopListSuccess(t)
            }
        }, mLifecycleProvider)
    }
}

