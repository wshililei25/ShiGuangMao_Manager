package com.qi.management.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.qi.management.R
import com.yizhipin.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_add_hint.*

class AddHintActivity : BaseActivity() {

    @Autowired(name = "title") //注解接收上个页面的传参
    @JvmField
    var mTitle: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_hint)

        mHeaderBar.getTiTleTv().text = mTitle;

    }

}
