package com.yizhipin.teacher.mine.paymentdeposit.mvp

import com.yizhipin.base.mvp.model.BaseModel
import com.yizhipin.base.mvp.view.BaseView

/**
 * Creator Qi
 * Date 2019/1/4
 */
interface PaymentDepositContract {
    interface IModel : BaseModel
    interface IPresenter {
        /**
         * 提交金额前，校验数据
         */
        fun verify(payType: String, amount: Double):Boolean
    }

    interface IView : BaseView {
        /**
         * 更新充值金额控件
         */
        fun updateAmount(amount: Double)
    }

}