package com.qi.management.store.production_management.view

import com.qi.management.bean.CommonDetailBean
import com.qi.management.bean.ProductionCategoryBean
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.mvp.view.BaseView

/**
 * Creator Qi
 * Date 2019/2/26
 */
interface ProductionListView : BaseView {
    fun showCategory(data: MutableList<ProductionCategoryBean>)
    fun addList(data: BasePagingResp<MutableList<CommonDetailBean>>)
}