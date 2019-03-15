package com.qi.management.home.stores.mvp

import com.qi.management.home.stores.mvp.StoresContract
import com.qi.management.home.stores.mvp.StoresModelImpl
import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 * <br/>
 * 首页部分公用页面Presenter
 */
class HomeGridPresenterImpl @Inject constructor(val model: StoresModelImpl, view: StoresContract.IView) : BasePresenter<StoresContract.IView>(view), StoresContract.IPresenter {
    object Style {
        const val Store = 0
        const val Person = 1
        const val Finance = 2
    }

}