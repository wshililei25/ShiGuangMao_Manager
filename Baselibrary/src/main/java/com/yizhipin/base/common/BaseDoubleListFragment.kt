package com.yizhipin.base.common

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.yizhipin.base.R
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.mvp.view.ListView
import com.yizhipin.base.recyclerview.BaseAdapter
import com.yizhipin.base.recyclerview.GridItemDecoration
import com.yizhipin.base.recyclerview.XGridItemDecoration
import com.yizhipin.base.ui.fragment.BaseMvpFragment

/**
 * Creator Qi
 * Date 2019/3/2
 */
abstract class BaseDoubleListFragment<P : BasePresenter<*>, Data, Adapter : BaseAdapter<Data, *>> : BaseMvpFragment<P>(), XRecyclerView.LoadingListener, ListView<Data> {

    private var adapter = createAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return XRecyclerView(inflater.context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (view as XRecyclerView).setPullRefreshEnabled(true)
        (view as XRecyclerView).setLoadingMoreEnabled(true)
        (view as XRecyclerView).layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        (view as XRecyclerView).addItemDecoration(XGridItemDecoration(context!!.resources.getDrawable(R.drawable.bg_rect_h5dp_solid_bg), context!!.resources.getDrawable(R.drawable.bg_rect_w5dp_solid_bg)))
//        (view as XRecyclerView).addItemDecoration(GridItemDecoration(context))
        (view as XRecyclerView).adapter = adapter
        (view as XRecyclerView).setLoadingListener(this)
        (view as XRecyclerView).refresh()
    }

    abstract fun createAdapter(): Adapter?

    abstract override fun onLoadMore()

    abstract override fun onRefresh()

    override fun add(data: MutableList<Data>) {
        if (adapter != null) {
            adapter!!.addAll(data)
        }
    }

    override fun remove(data: Data) {
        if (adapter != null) {
            adapter!!.remove(data)
        }
    }

    override fun remove(data: MutableList<Data>) {
        if (adapter != null) adapter!!.remove(data)
    }

    override fun getAll(): MutableList<Data> {
        return if (adapter != null)
            adapter!!.getAll()
        else ArrayList()
    }

    override fun getItem(position: Int): Data? {
        return if (adapter != null) adapter!!.getItem(position)
        else null
    }

    override fun hideLoading() {
        super.hideLoading()
        if (view != null) {
            (view as XRecyclerView).refreshComplete()
            (view as XRecyclerView).loadMoreComplete()
        }
    }
}