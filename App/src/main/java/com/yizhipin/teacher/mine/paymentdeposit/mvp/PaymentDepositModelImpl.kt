package com.yizhipin.teacher.mine.paymentdeposit.mvp

import com.yizhipin.base.data.net.RetrofitFactory
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.BeanRechargeDeposit
import com.yizhipin.usercenter.data.api.Api
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 */
class PaymentDepositModelImpl @Inject constructor() : PaymentDepositContract.IModel {
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * @param uid 用户token
     * @param amount 充值金额
     * @param payType 支付方式
     */
    fun recharge(uid: String, amount: Double, payType: String): Observable<BaseResp<BeanRechargeDeposit>> {
        return RetrofitFactory.instance.create(Api::class.java).recharge(uid, amount, payType)
    }
}