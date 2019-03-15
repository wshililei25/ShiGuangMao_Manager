package com.yizhipin.teacher.mine.cashpledge

import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.yizhipin.R
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.BeanDeposit
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.teacher.mine.cashpledge.dagger.CashPledgeModule
import com.yizhipin.teacher.mine.cashpledge.dagger.DaggerCashPledgeComponent
import com.yizhipin.teacher.mine.cashpledge.mvp.CashPledgePresenter
import com.yizhipin.teacher.mine.cashpledge.mvp.CashPledgeView
import com.yizhipin.teacher.mine.depositrefund.DepositRefundActivity
import com.yizhipin.ui.fragment.MeFragment
import kotlinx.android.synthetic.main.activity_deposit.*

/**
 * Creator Qi
 * Date 2018/12/30
 * <p>押金</p>
 */
@Route(path = RouterPath.UserCenter.DEPOSIT)
class DepositActivity : BaseMvpActivity<CashPledgePresenter>(), CashPledgeView {

    override fun onCreateView(): Int {
        return R.layout.activity_deposit
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
//        titleView.setOnLeftIconClickListener { onBackPressed() }
        rechargeDepositBtn.setOnClickListener {
            ARouter.getInstance().build(RouterPath.PayCenter.PATH_PAY_RECHARGE)
                    .withBoolean(BaseConstant.KEY_IS_FIRST, true).navigation()
        }
        depositRefundBtn.setOnClickListener { DepositRefundActivity.startActivity(this) }
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mBasePresenter.getDepositByUID()//获取押金信息
    }

    override fun injectComponent() {
        DaggerCashPledgeComponent.builder().activityComponent(mActivityComponent).cashPledgeModule(CashPledgeModule(this)).build().inject(this)
    }

    override fun showDeposit(data: BeanDeposit) {
        cashPledgeText.text = resources.getString(R.string.cashPledgeMoney, data.total, data.available)
    }

    companion object {
        fun startActivity(fragment: MeFragment) {
            val intent = Intent(fragment.context, DepositActivity::class.java)
            fragment.startActivity(intent)
        }
    }
}