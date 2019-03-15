package com.yizhipin.teacher.schedule.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.teacher.schedule.presenter.SchedulePresenter
import kotlinx.android.synthetic.main.fragment_schedule.*

/**
 * Creator Qi
 * Date 2018/12/8
 * <p>排期</p>
 */
class ScheduleFragment : BaseMvpFragment<SchedulePresenter>() {

    /**
     * 排期类型
     */
    enum class ScheduleViewType {
        /**日历类型*/
        Calendar,
        /**列表展示类型*/
        List
    }

    private val scheduleCalendarFragment = ScheduleCalendarFragment()
    private val scheduleListFragment = ScheduleListFragment()
    /**排期默认展示日历视图*/
    private var isCalendarShown = true

    override fun injectComponent() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_schedule, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mHead.getRightTv().setOnClickListener {
            //切换排期视图
            var transaction = childFragmentManager.beginTransaction()
            if (isCalendarShown) {
                transaction.show(scheduleListFragment)
                transaction.hide(scheduleCalendarFragment)
            } else {
                transaction.show(scheduleCalendarFragment)
                transaction.hide(scheduleListFragment)
            }
            transaction.commit()
            isCalendarShown = !isCalendarShown
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val transaction = childFragmentManager.beginTransaction()
        transaction.add(R.id.contentView, scheduleCalendarFragment, ScheduleViewType.Calendar.name)
        transaction.add(R.id.contentView, scheduleListFragment, ScheduleViewType.List.name)
        transaction.show(scheduleCalendarFragment)
        transaction.hide(scheduleListFragment)
        transaction.commit()
    }

}