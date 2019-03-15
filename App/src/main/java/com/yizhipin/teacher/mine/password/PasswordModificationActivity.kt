package com.yizhipin.teacher.mine.password

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.teacher.mine.password.mvp.PasswordModificationContract
import com.yizhipin.teacher.mine.password.mvp.PasswordModificationPresenterImpl
import kotlinx.android.synthetic.main.activity_passwordmodification.*

/**
 * Creator Qi
 * Date 2018/12/30
 */
class PasswordModificationActivity : BaseMvpActivity<PasswordModificationPresenterImpl>(), PasswordModificationContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)
    }

    override fun injectComponent() {

    }

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, PasswordModificationActivity::class.java)
            context.startActivity(intent)
        }
    }

}