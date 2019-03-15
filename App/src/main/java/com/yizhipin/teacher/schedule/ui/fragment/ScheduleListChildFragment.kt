package com.yizhipin.teacher.schedule.ui.fragment

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcodecraeer.xrecyclerview.ProgressStyle
import com.jcodecraeer.xrecyclerview.XRecyclerView
import com.yizhipin.R
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.data.response.ScheduleItemBean
import com.yizhipin.teacher.ScheduleListView
import com.yizhipin.teacher.dagger.component.DaggerScheduleListComponent
import com.yizhipin.teacher.dagger.module.ScheduleListModule
import com.yizhipin.teacher.schedule.presenter.ScheduleListPresenter
import com.yizhipin.teacher.schedule.ui.adapter.ScheduleListAdapter
import kotlinx.android.synthetic.main.fragment_schedule_list_child.*

/**
 * Creator Qi
 * Date 2018/12/19
 */
class ScheduleListChildFragment : BaseMvpFragment<ScheduleListPresenter>(), ScheduleListView {

    object BundleKeys {
        const val SCHEDULE_STATUS = "SCHEDULE_STATUS"
    }

    object Status {
        const val UNFINISHED = 0
        const val FINISHED = 1
    }

    var pageIndex :Int = 0
    var scheduleStatus: Int = 0
    private lateinit var scheduleListAdapter: ScheduleListAdapter

    override fun injectComponent() {
        DaggerScheduleListComponent.builder().activityComponent(mActivityComponent).scheduleListModule(ScheduleListModule(this)).build().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule_list_child, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        scheduleStatus = arguments!![BundleKeys.SCHEDULE_STATUS] as Int
        //初始化RecyclerView
        initRecyclerView()
        //初始化数据，从网络获取列表数据
        recyclerView.refresh()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)//设置RecyclerView方向
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.bg_h_05dp_solid_divider))
        recyclerView.addItemDecoration(dividerItemDecoration)//添加分割线
        recyclerView.setLoadingMoreEnabled(true)
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.SysProgress)//刷新样式
        scheduleListAdapter = ScheduleListAdapter()
        recyclerView.adapter = scheduleListAdapter
        recyclerView.setLoadingListener(object : XRecyclerView.LoadingListener {
            override fun onLoadMore() {
                pageIndex++
                mBasePresenter.getScheduleList(pageIndex,scheduleStatus)
            }

            override fun onRefresh() {
                pageIndex = 0
                mBasePresenter.getScheduleList(pageIndex,scheduleStatus)
            }

        })
    }

    override fun show(pagingResp: BasePagingResp<MutableList<ScheduleItemBean>>) {
       if (pagingResp.pi.currentPage==0)
           scheduleListAdapter.setData(pagingResp.data)
        scheduleListAdapter.addData(pagingResp.data)
    }

    override fun showLoading() {
        super.showLoading()
    }

    override fun hideLoading() {
        super.hideLoading()
        recyclerView.refreshComplete()
    }
}