package com.yizhipin.teacher.schedule.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.yizhipin.BuildConfig
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_version_info.*

/**
 * Created by ${XiLei} on 2018/8/21.
 */
class VersionInfoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_version_info)
        initView()
    }

    private fun initView() {
//        titleView.setOnLeftIconClickListener { onBackPressed() }
        mAboutTv.text = getString(R.string.system_version).plus(BuildConfig.VERSION_NAME)
    }

    companion object {
        fun startActivity(context: Context){
            context.startActivity(Intent(context, VersionInfoActivity::class.java))
        }
    }
}