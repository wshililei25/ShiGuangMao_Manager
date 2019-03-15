package com.qi.management.store.production_management.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.qi.management.R
import com.qi.management.bean.CommonDetailBean
import com.qi.management.bean.ProductionCategoryBean
import com.qi.management.store.production_management.ProductionListAdapter
import com.qi.management.store.production_management.dagger.DaggerProductionListComponent
import com.qi.management.store.production_management.dagger.ProductionListModule
import com.qi.management.store.production_management.presenter.ProductionListPresenterImpl
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.recyclerview.XGridItemDecoration
import com.yizhipin.base.ui.fragment.BaseMvpFragment

/**
 * 产品列表
 * Creator Qi
 * Date 2019/2/26
 */
class ProductionListFragment : BaseMvpFragment<ProductionListPresenterImpl>(), ProductionListView, XRecyclerView.LoadingListener {

    companion object {
        const val ARG_PAGE_TYPE = "ARG_PAGE_TYPE"
    }

    val adapter = ProductionListAdapter()

    override fun injectComponent() {
        DaggerProductionListComponent.builder()
                .activityComponent(mActivityComponent)
                .productionListModule(ProductionListModule(this))
                .build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return XRecyclerView(inflater.context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (view as XRecyclerView).setPullRefreshEnabled(true)
        (view as XRecyclerView).setLoadingMoreEnabled(true)
        (view as XRecyclerView).layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        (view as XRecyclerView).addItemDecoration(XGridItemDecoration(context!!.resources.getDrawable(R.drawable.bg_rect_w5dp_solid_bg), context!!.resources.getDrawable(R.drawable.bg_rect_h5dp_solid_bg)))
        (view as XRecyclerView).adapter = adapter
        (view as XRecyclerView).setLoadingListener(this)
        mBasePresenter.categoryID = arguments!!.getLong(ARG_PAGE_TYPE)
        (view as XRecyclerView).refresh()
    }

    fun setCategoryID(categoryID: Long): ProductionListFragment {
        val bundle = Bundle()
        bundle.putLong(ARG_PAGE_TYPE, categoryID)
        arguments = bundle
        return this
    }

    override fun onLoadMore() {
        mBasePresenter.getProductionList()
    }

    override fun onRefresh() {
        mBasePresenter.getProductionList()
    }

    override fun addList(data: BasePagingResp<MutableList<CommonDetailBean>>) {
        adapter.addAll(data.data)
    }

    //不用实现
    override fun showCategory(data: MutableList<ProductionCategoryBean>) {
    }

    override fun hideLoading() {
        super.hideLoading()
        (view as XRecyclerView).refreshComplete()
        (view as XRecyclerView).loadMoreComplete()
    }
}