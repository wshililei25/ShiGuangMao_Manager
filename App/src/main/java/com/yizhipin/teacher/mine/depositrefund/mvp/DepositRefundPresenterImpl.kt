package com.yizhipin.teacher.mine.depositrefund.mvp

import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 */
class DepositRefundPresenterImpl @Inject constructor(val model: DepositRefundModelImpl, view: DepositRefundContract.IView) : BasePresenter<DepositRefundContract.IView>(view), DepositRefundContract.IPresenter {

}