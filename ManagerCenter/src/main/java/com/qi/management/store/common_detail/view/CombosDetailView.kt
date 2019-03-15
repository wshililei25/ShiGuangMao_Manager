package com.qi.management.store.common_detail.view

import com.qi.management.bean.CommonDetailBean
import com.yizhipin.base.mvp.view.BaseView

/**
 * Creator Qi
 * Date 2019/2/24
 */
interface CombosDetailView : BaseView {

    fun show(bean: CommonDetailBean)
    fun showSuggestion(data: MutableList<CommonDetailBean>)

}