package com.qi.management.store.costume.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qi.management.R
import com.qi.management.bean.CommonDetailBean
import com.qi.management.bean.CostumeCategoryBean
import com.qi.management.store.costume.list.adapter.CostumeListAdapter
import com.qi.management.store.costume.list.dagger.CostumeListModule
import com.qi.management.store.costume.list.dagger.DaggerCostumeListComponent
import com.qi.management.store.costume.list.presenter.CostumeListPresenterImpl
import com.qi.management.store.costume.list.view.CostumeListActivity.Param.CATEGORY
import com.qi.management.store.costume.list.view.CostumeListActivity.Param.SEX
import com.qi.management.store.costume.list.view.CostumeListActivity.Param.sell_TYPE
import com.yizhipin.base.common.BaseDoubleListFragment

/**
 * Creator Qi
 * Date 2019/2/28
 */
class CostumeListChildFragment : BaseDoubleListFragment<CostumeListPresenterImpl, CommonDetailBean, CostumeListAdapter>(), CostumeListView {

    override fun injectComponent() {
        DaggerCostumeListComponent.builder().activityComponent(mActivityComponent).costumeListModule(CostumeListModule(this)).build().inject(this)
    }

    fun setParams(sellType: Int, sex: Int, category: Int): CostumeListChildFragment {
        val bundle = Bundle()
        bundle.putInt(CostumeListActivity.sell_TYPE, sellType)
        bundle.putInt(CostumeListActivity.SEX, sex)
        bundle.putInt(CostumeListActivity.CATEGORY, category)
        arguments = bundle
        return this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_child_costume_list, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        mBasePresenter.sellType = arguments?.getInt(sell_TYPE)!!
        mBasePresenter.sex = arguments?.getInt(SEX)!!
        mBasePresenter.category = arguments?.getInt(CATEGORY)!!
        super.onActivityCreated(savedInstanceState)
    }

    override fun createAdapter(): CostumeListAdapter? {
        return CostumeListAdapter()
    }

    override fun showCategory(categoryList: List<CostumeCategoryBean>) {

    }

    override fun onLoadMore() {
        mBasePresenter.getCostumeList()
    }

    override fun onRefresh() {
        mBasePresenter.getCostumeList()
    }
}