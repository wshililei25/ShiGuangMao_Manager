package com.qi.management.store.production_management.view

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.qi.management.R
import com.qi.management.bean.CommonDetailBean
import com.qi.management.bean.ProductionCategoryBean
import com.qi.management.store.production_management.dagger.DaggerProductionListComponent
import com.qi.management.store.production_management.dagger.ProductionListModule
import com.qi.management.store.production_management.presenter.ProductionListPresenterImpl
import com.qi.management.store.wedding_photography.comboslist.adapter.CombosPagerAdapter
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.provider.router.RouterPath.Management.PRODUCTION_LIST
import kotlinx.android.synthetic.main.activity_combos_list.*

/**
 * 产品管理列表页
 * Creator Qi
 * Date 2019/2/26
 */
@Route(path = PRODUCTION_LIST)
class ProductionListActivity : BaseMvpActivity<ProductionListPresenterImpl>(), ProductionListView {

    var pagerItems: MutableList<CombosPagerAdapter.CombosPagerItem> = arrayListOf()

    override fun injectComponent() {
        DaggerProductionListComponent.builder()
                .activityComponent(mActivityComponent)
                .productionListModule(ProductionListModule(this))
                .build().inject(this)
    }


    override fun onCreateView(): Int {
        return R.layout.activity_combos_list
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
//        titleView.setTitle(R.string.title_production_manage)
//        titleView.setOnLeftIconClickListener { onBackPressed() }
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mBasePresenter.getProductionCategory()
    }

    override fun showCategory(data: MutableList<ProductionCategoryBean>) {
        for (i in 0 until data.size) {
            tabLayout.addTab(tabLayout.newTab().setText(data[i].name))
            pagerItems.add(CombosPagerAdapter.CombosPagerItem(ProductionListFragment().setCategoryID(data[i].id),data[i].name))
        }
        tabLayout.setupWithViewPager(viewPager)
        viewPager.adapter = CombosPagerAdapter(supportFragmentManager,pagerItems)
    }

    override fun addList(data: BasePagingResp<MutableList<CommonDetailBean>>) {
    }

}