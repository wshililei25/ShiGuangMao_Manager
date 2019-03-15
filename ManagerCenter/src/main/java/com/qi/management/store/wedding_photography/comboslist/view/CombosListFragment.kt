package com.qi.management.store.wedding_photography.comboslist.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.VERTICAL
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.qi.management.R
import com.qi.management.bean.CommonDetailBean
import com.qi.management.store.wedding_photography.comboslist.CombosListContract
import com.qi.management.store.wedding_photography.comboslist.adapter.CombosListAdapter
import com.qi.management.store.wedding_photography.comboslist.dagger.CombosListModule
import com.qi.management.store.wedding_photography.comboslist.dagger.DaggerCombosListComponent
import com.qi.management.store.wedding_photography.comboslist.presenter.CombosListPresenterImpl
import com.yizhipin.base.ui.fragment.BaseMvpFragment

/**
 * 套餐列表
 * Creator Qi
 * Date 2019/2/24
 */
class CombosListFragment : BaseMvpFragment<CombosListPresenterImpl>(), CombosListContract.CombosListView, XRecyclerView.LoadingListener {

    companion object {

        const val COMBOS_TYPE = "COMBOS_TYPE"
        const val PHOTO_TYPE = "PHOTO_TYPE"

        /**
         * 全部
         */
        const val ALL = -1
        /**
         * 套餐
         */
        const val COMBOS = 0
        /**
         * 私人订制
         */
        const val PERSONAL = 1
    }

    private val adapter = CombosListAdapter()
    private var photoType: String = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(container?.context, R.layout.fragment_combos_list, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (view as XRecyclerView).addItemDecoration((view as XRecyclerView).DividerItemDecoration(resources.getDrawable(android.R.drawable.divider_horizontal_bright)))
        (view as XRecyclerView).layoutManager = LinearLayoutManager(context, VERTICAL, false)
        (view as XRecyclerView).adapter = adapter
        (view as XRecyclerView).setLoadingMoreEnabled(true)
        (view as XRecyclerView).setPullRefreshEnabled(true)
        (view as XRecyclerView).setLoadingListener(this)

        photoType = arguments!!.getString(PHOTO_TYPE)
        (view as XRecyclerView).refresh()

    }

    override fun injectComponent() {
        DaggerCombosListComponent.builder().activityComponent(mActivityComponent).combosListModule(CombosListModule(this)).build().inject(this)
    }

    override fun onLoadMore() {
        mBasePresenter.getCombosList(if (arguments?.getInt(COMBOS_TYPE) == ALL) null else arguments?.getInt(COMBOS_TYPE),photoType)
    }

    override fun onRefresh() {
        mBasePresenter.getCombosList(if (arguments?.getInt(COMBOS_TYPE) == ALL) null else arguments?.getInt(COMBOS_TYPE),photoType)
    }

    override fun add(data: MutableList<CommonDetailBean>) {
        adapter.add(data)
    }

    override fun hideLoading() {
        super.hideLoading()
        (view as XRecyclerView).refreshComplete()
        (view as XRecyclerView).loadMoreComplete()
    }

    fun setCombosType(combosType: Int, photoType: String): CombosListFragment {
        val bundle = Bundle()
        bundle.putInt(CombosListFragment.COMBOS_TYPE, combosType)
        bundle.putString(CombosListFragment.PHOTO_TYPE, photoType)
        arguments = bundle
        return this
    }
}