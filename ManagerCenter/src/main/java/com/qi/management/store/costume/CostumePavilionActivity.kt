package com.qi.management.store.costume

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.qi.management.R
import com.qi.management.store.costume.list.view.CostumeListActivity
import com.yizhipin.base.ui.activity.BaseActivity
import com.yizhipin.provider.router.RouterPath.Management.COSTUME_PAVILION
import kotlinx.android.synthetic.main.activity_costume_pavilion.*

/**
 * 服装馆
 * Creator Qi
 * Date 2019/2/28
 */
@Route(path = COSTUME_PAVILION)
class CostumePavilionActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_costume_pavilion)
//        titleView.setOnLeftIconClickListener { onBackPressed() }
        saleImage.setOnClickListener { CostumeListActivity.navigation(CostumeListActivity.SellType.Sale) }
        shareImage.setOnClickListener {CostumeListActivity.navigation(CostumeListActivity.SellType.Share) }
    }
}