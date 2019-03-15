package com.yizhipin.teacher.mine.cashpledge.mvp

import com.yizhipin.base.data.response.BeanDeposit
import com.yizhipin.base.mvp.view.BaseView

/**
 * Creator Qi
 * Date 2018/12/30
 */
interface CashPledgeView : BaseView {
    /**
     * 展示押金
     */
    fun showDeposit(data: BeanDeposit)
}