package com.qi.management.store.wedding_photography.comboslist

import com.qi.management.bean.CommonDetailBean
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.mvp.view.BaseView
import io.reactivex.Observable

/**
 * 套餐选择列表MVP Contract
 * Creator Qi
 * Date 2019/2/24
 */
interface CombosListContract {
    interface CombosListModel {
        /**
         * @param combosType 套餐类型(0套餐,1私人定制)
         * @param currentPage 当前分页数
         */
        fun getCombosList(combosType: Int?, photoType: String,currentPage: Int): Observable<BasePagingResp<MutableList<CommonDetailBean>>>?
    }

    interface CombosListView : BaseView {
        fun add(data: MutableList<CommonDetailBean>)
    }

    interface CombosListPresenter
}