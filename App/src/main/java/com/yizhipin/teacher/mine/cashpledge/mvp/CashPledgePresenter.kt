package com.yizhipin.teacher.mine.cashpledge.mvp

import com.yizhipin.base.data.response.BeanDeposit
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.CodeHandlerSubscriber
import com.yizhipin.usercenter.utils.UserPrefsUtils
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 * <br/>
 * 押金操作类
 */
class CashPledgePresenter @Inject constructor(val model: CashPledgeModel, view: CashPledgeView) : BasePresenter<CashPledgeView>(view) {
    /**
     * 获取押金
     */
    fun getDepositByUID() {
        model.getDepositByUID(UserPrefsUtils.getUserId()).execute(object : CodeHandlerSubscriber<BeanDeposit>(mView){
            override fun onSucceed(data: BeanDeposit) {
                mView.showDeposit(data)
            }

        },mLifecycleProvider)
    }
}