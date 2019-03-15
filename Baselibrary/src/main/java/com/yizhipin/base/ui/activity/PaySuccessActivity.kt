package com.yizhipin.base.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.yizhipin.base.R
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ext.setVisible
import kotlinx.android.synthetic.main.activity_pay_success.*

/**
 *  @author: XiLei  @date: 2019/2/26
 */
class PaySuccessActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_success)
        mHeaderBar.getBackIv().setVisible(false)
        mContentTv.text = intent.getStringExtra(BaseConstant.KEY_PAY_CONTENT)
        mConfirmBtn.onClick {
            ARouter.getInstance().build("/app/main").navigation()
            finish()
        }
    }
}