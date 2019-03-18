package com.qi.management.presenter

import com.qi.management.presenter.view.StoreInfoView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.data.response.Store
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class StoreInfoPresenter @Inject constructor() : BasePresenter<StoreInfoView>() {

    @Inject
    lateinit var mServiceImpl: ManagerServiceImpl

    fun getStoreInfo(map: MutableMap<String, String>) {
        mServiceImpl.getStoreInfo(map).execute(object : BaseSubscriber<Store>(mView) {
            override fun onNext(t: Store) {
                mView.onGetStoreInfoResult(t)
            }
        }, mLifecycleProvider)
    }


}