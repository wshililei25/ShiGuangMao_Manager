package com.yizhipin.teacher

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.mvp.view.BaseView
import com.yizhipin.data.response.ScheduleItemBean
import com.yizhipin.usercenter.bean.Banner

/**
 * Creator Qi
 * Date 2018/12/8
 */
/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface HomeView : BaseView {
    fun onGetBannerSuccess(result: MutableList<Banner>)
    fun onGetGoodsListSuccess(result: MutableList<Goods>)
    fun onGetOssAddressSuccess(result: OssAddress)
}

/**
 * 抢单
 */
interface GrabView : BaseView {
    fun showData(data: MutableList<ScheduleItemBean>)
}

interface ScheduleView : BaseView {
    fun switchView()
}

/**
 * 日程安排日历展示
 */
interface ScheduleCalendarView : BaseView {
    fun uploadSchedule(scheduleMap: HashMap<String, ScheduleItemBean>)
}

interface ScheduleListView : BaseView {
    fun show(data: BasePagingResp<MutableList<ScheduleItemBean>>)
}

