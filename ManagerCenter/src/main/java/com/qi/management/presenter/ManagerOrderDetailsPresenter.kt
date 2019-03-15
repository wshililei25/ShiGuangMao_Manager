package com.qi.management.presenter

import com.qi.management.presenter.view.ManagerOrderDetailsView
import com.qi.management.service.impl.ManagerServiceImpl
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.BaseSubscriber
import com.yizhipin.usercenter.data.response.ManagerOrderDetails
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class ManagerOrderDetailsPresenter @Inject constructor() : BasePresenter<ManagerOrderDetailsView>() {

    @Inject
    lateinit var mServiceImpl: ManagerServiceImpl

    /*   fun getMealDetails(map: MutableMap<String, String>) {
           mView.showLoading()
           mServiceImpl.getMealDetails(map).execute(object : BaseSubscriber<SetMealDetails>(mView) {
               override fun onNext(t: SetMealDetails) {
                   mView.onGetMealDetailsSuccess(t)
               }
           }, mLifecycleProvider)
       }

       fun order(map: MutableMap<String, String>) {
           mView.showLoading()
           mServiceImpl.order(map).execute(object : BaseSubscriber<OrderDetails>(mView) {
               override fun onNext(t: OrderDetails) {
                   mView.onOrderSuccess(t)
               }
           }, mLifecycleProvider)
       }*/

    fun getManagerOrderDetails(map: MutableMap<String, String>) {
        mView.showLoading()
        mServiceImpl.getManagerOrderDetails(map).execute(object : BaseSubscriber<ManagerOrderDetails>(mView) {
            override fun onNext(t: ManagerOrderDetails) {
                mView.onGetManagerOrderDetailsSuccess(t)
            }
        }, mLifecycleProvider)
    }

    /*fun getEvaluateData(map: MutableMap<String, String>) {
        mView.showLoading()
        mServiceImpl.getEvaluateData(map).execute(object : BaseSubscriber<MutableList<Evaluate>>(mView) {
            override fun onNext(t: MutableList<Evaluate>) {
                mView.onGetEvaluateSuccess(t)
            }
        }, mLifecycleProvider)
    }

    fun getBasicServicesData(map: MutableMap<String, String>) {
        mView.showLoading()
        mServiceImpl.getBasicServicesData(map).execute(object : BaseSubscriber<MutableList<BasicServices>>(mView) {
            override fun onNext(t: MutableList<BasicServices>) {
                mView.onGetBasicServicesSuccess(t)
            }
        }, mLifecycleProvider)
    }

    fun getFollow(map: MutableMap<String, String>) {
        mView.showLoading()
        mServiceImpl.getFollow(map).execute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onFollowSuccess(t)
            }
        }, mLifecycleProvider)
    }*/
}

