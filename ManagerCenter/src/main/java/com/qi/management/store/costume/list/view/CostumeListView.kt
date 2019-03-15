package com.qi.management.store.costume.list.view

import com.qi.management.bean.CostumeCategoryBean
import com.yizhipin.base.mvp.view.BaseView

/**
 * Creator Qi
 * Date 2019/2/28
 */
interface CostumeListView : BaseView {
    fun showCategory(categoryList: List<CostumeCategoryBean>)
}