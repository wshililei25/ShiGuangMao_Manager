package com.yizhipin.teacher.mine.baseinfo

import android.os.Bundle
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.teacher.mine.baseinfo.dagger.BaseInfoModule
import com.yizhipin.teacher.mine.baseinfo.dagger.DaggerBaseInfoComponent
import com.yizhipin.teacher.mine.baseinfo.mvp.BaseInfoContract
import com.yizhipin.teacher.mine.baseinfo.mvp.BaseInfoPresenterImpl
import kotlinx.android.synthetic.main.activity_base_info.*

/**
 * Creator Qi
 * Date 2018/12/30
 */
class BaseInfoActivity : BaseMvpActivity<BaseInfoPresenterImpl>(), BaseInfoContract.BaseInfoView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_info)
//        titleView.setOnLeftIconClickListener { onBackPressed() }
    }

    override fun injectComponent() {
        DaggerBaseInfoComponent.builder().activityComponent(mActivityComponent).baseInfoModule(BaseInfoModule(this)).build().inject(this)
    }


}