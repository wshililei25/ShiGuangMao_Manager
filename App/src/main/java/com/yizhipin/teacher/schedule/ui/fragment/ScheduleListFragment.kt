package com.yizhipin.teacher.schedule.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.teacher.schedule.presenter.ScheduleListPresenter
import com.yizhipin.teacher.schedule.ui.adapter.ScheduleListPagerAdapter
import kotlinx.android.synthetic.main.fragment_schdule_list.*

/**
 * Creator Qi
 * Date 2018/12/8
 * <p>排期列表展示</p>
 */
class ScheduleListFragment : BaseMvpFragment<ScheduleListPresenter>() {
    override fun injectComponent() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schdule_list, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTabLayout.addTab(mTabLayout.newTab())
        mTabLayout.addTab(mTabLayout.newTab())
        mTabLayout.setupWithViewPager(mViewPager)
        //待完成
        val unfinishedFragment = ScheduleListChildFragment()
        var bundle = Bundle()
        bundle.putInt(ScheduleListChildFragment.BundleKeys.SCHEDULE_STATUS, ScheduleListChildFragment.Status.FINISHED)
        unfinishedFragment.arguments = bundle
        //已完成
        val finishedFragment = ScheduleListChildFragment()
        bundle = Bundle()
        bundle.putInt(ScheduleListChildFragment.BundleKeys.SCHEDULE_STATUS, ScheduleListChildFragment.Status.UNFINISHED)
        finishedFragment.arguments = bundle
        mViewPager.adapter = ScheduleListPagerAdapter(arrayOf(unfinishedFragment, finishedFragment), childFragmentManager)
        mTabLayout.getTabAt(0)!!.setText(R.string.tab_unfinished)
        mTabLayout.getTabAt(1)!!.setText(R.string.tab_finished)
    }
}