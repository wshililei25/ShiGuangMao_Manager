package com.yizhipin.base.rx

import com.yizhipin.base.R
import com.yizhipin.base.common.BaseApplication
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.mvp.view.BaseView
import com.yizhipin.base.utils.ToastUtils
import io.reactivex.disposables.Disposable
import retrofit2.HttpException


/**
 * Created by ${XiLei} on 2018/7/26.
 */
abstract class CodeListHandlerSubscriber<T>(private val baseView: BaseView) : BaseSubscriber<BasePagingResp<T>>(baseView) {
    override fun onSubscribe(d: Disposable) {
        baseView.showLoading()
    }

    override fun onNext(resp: BasePagingResp<T>) {
        super.onNext(resp)
        when {
            "00" == resp.code -> onSucceed(resp)
            "401" == resp.code -> ToastUtils.INSTANCE.showToast(BaseApplication.context, R.string.errorNetUnauthorized)
            "403" == resp.code -> ToastUtils.INSTANCE.showToast(BaseApplication.context, R.string.errorNetForbidden)
            "404" == resp.code -> ToastUtils.INSTANCE.showToast(BaseApplication.context, R.string.errorNetNotFound)
            else -> ToastUtils.INSTANCE.showToast(BaseApplication.context, resp.msg)
        }
    }

    override fun onError(e: Throwable) {
        baseView.hideLoading()
        when (e) {
            is BaseException -> baseView.onError(e.msg)
            is DataNullException -> baseView.onDataIsNull()
            is HttpException -> when (e.code()) {
                400 -> ToastUtils.INSTANCE.showToast(BaseApplication.context, R.string.errorCode400)
                403 -> ToastUtils.INSTANCE.showToast(BaseApplication.context, R.string.errorCode403)
                404 -> ToastUtils.INSTANCE.showToast(BaseApplication.context, R.string.errorCode404)
                500 -> ToastUtils.INSTANCE.showToast(BaseApplication.context, R.string.errorCode500)
            }
        }
    }

    override fun onComplete() {
        baseView.hideLoading()
    }

    abstract fun onSucceed(data: BasePagingResp<T>)
}