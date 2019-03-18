package com.yizhipin.teacher.mine.paymentdeposit.mvp

import android.app.Activity
import android.text.TextUtils
import com.yizhipin.R
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.BeanRechargeDeposit
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.CodeHandlerSubscriber
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.usercenter.utils.UserPrefsUtils
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 */
class PaymentDepositPresenterImpl @Inject constructor(val model: PaymentDepositModelImpl, view: PaymentDepositContract.IView) : BasePresenter<PaymentDepositContract.IView>(view), PaymentDepositContract.IPresenter {

    var amount: Double = 0.0

    override fun verify(payType: String, amount: Double): Boolean {
        if (amount <= 0) {
//            mView.showMsg(R.string.hintRechargeAmount)
            return false
        } else if (TextUtils.isEmpty(payType)) {
//            mView.showMsg(R.string.hintPayType)
            return false
        }
        return true
    }

    /**
     * 提交充值金额
     * @param payType 支付方式
     */
    fun recharge(payType: String) {
        if (!verify(payType, amount)) return
        model.recharge(AppPrefsUtils.getString(BaseConstant.KEY_SP_USER_ID), amount, payType).execute(object : CodeHandlerSubscriber<BeanRechargeDeposit>(mView) {
            override fun onSucceed(data: BeanRechargeDeposit) {
                if (data.paySuccess && mView != null && mView is Activity)
                    (mView as Activity).finish()
            }
        }, mLifecycleProvider)
    }

    fun increase() {
        amount += 10
        if (mView != null)
            mView.updateAmount(amount)
    }

    fun decrement() {
        amount -= 10
        if (mView != null)
            mView.updateAmount(amount)
    }

    fun initData() {
        amount = 0.0
    }

}