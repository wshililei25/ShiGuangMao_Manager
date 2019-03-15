package com.yizhipin.teacher.mine.depositrefund

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.teacher.mine.depositrefund.dagger.DaggerComponent
import com.yizhipin.teacher.mine.depositrefund.dagger.Module

import com.yizhipin.teacher.mine.depositrefund.mvp.DepositRefundContract
import com.yizhipin.teacher.mine.depositrefund.mvp.DepositRefundPresenterImpl
import kotlinx.android.synthetic.main.activity_depositrefund.*

/**
 * Creator Qi
 * Date 2018/12/30
 * <p>退押金</p>
 */
class DepositRefundActivity : BaseMvpActivity<DepositRefundPresenterImpl>(), DepositRefundContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_depositrefund)
//        titleView.setOnLeftIconClickListener { onBackPressed() }
    }

    override fun injectComponent() {
        DaggerComponent.builder().activityComponent(mActivityComponent).module(Module(this)).build().inject(this)
    }

    companion object {
        fun startActivity(activity: Activity) {
            val intent = Intent(activity, DepositRefundActivity::class.java)
            activity.startActivity(intent)
        }
    }

}