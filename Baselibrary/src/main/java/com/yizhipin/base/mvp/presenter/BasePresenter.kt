package com.yizhipin.base.mvp.presenter

import android.content.Context
import com.trello.rxlifecycle2.LifecycleProvider
import com.yizhipin.base.mvp.view.BaseView
import com.yizhipin.base.utils.NetWorkUtils
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class BasePresenter<T : BaseView> constructor() {
    lateinit var mView: T

    constructor(view: T) : this() {
        mView = view
    }


    @Inject
    lateinit var mLifecycleProvider: LifecycleProvider<*>
    @Inject
    lateinit var context: Context

    fun checkNetWork(): Boolean {
        if (NetWorkUtils.isNetWorkAvailable(context)) {
            return true
        }
        mView.onError("网络不可用")
        return false
    }
}