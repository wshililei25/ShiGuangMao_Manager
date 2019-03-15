package com.qi.management.home.stores

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import com.qi.management.R
import com.yizhipin.base.recyclerview.GridItemDecoration
import com.qi.management.home.stores.adapter.HomeGridAdapter
import com.qi.management.home.stores.dagger.DaggerComponent
import com.qi.management.home.stores.dagger.Module
import com.qi.management.home.stores.mvp.HomeGridPresenterImpl
import com.qi.management.home.stores.mvp.StoresContract
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_home_grid.*

/**
 * Creator Qi
 * Date 2018/12/30
 * <br/>
 */
class HomeGridFragment : BaseMvpFragment<HomeGridPresenterImpl>(), StoresContract.IView {
    private val homeGridAdapter = HomeGridAdapter()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_grid, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.addItemDecoration(GridItemDecoration(context))
        recyclerView.layoutManager = GridLayoutManager(context, 3, GridLayout.VERTICAL, false)
        recyclerView.adapter = homeGridAdapter
        changeStyle(HomeGridPresenterImpl.Style.Store)
    }

    override fun injectComponent() {
        DaggerComponent.builder().activityComponent(mActivityComponent).module(Module(this)).build().inject(this)
    }

    fun changeStyle(style: Int) {
        homeGridAdapter.setStyle(style)
        when (style) {
//            HomeGridPresenterImpl.Style.Store -> titleView.setTitle(R.string.titleStoreManagement)
//            HomeGridPresenterImpl.Style.Person -> titleView.setTitle(R.string.titlePersonerManagement)
//            HomeGridPresenterImpl.Style.Finance -> titleView.setTitle(R.string.titleFinanceManagement)
        }
    }


}