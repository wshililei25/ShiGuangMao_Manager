package com.qi.management.store.common_detail.model

import com.qi.management.bean.CommonDetailBean
import com.qi.management.store.common_detail.view.CommonDetailActivity
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.mvp.model.BaseModel
import io.reactivex.Observable

/**
 * 详情页
 * Creator Qi
 * Date 2019/2/24
 */
interface CombosDetailModel : BaseModel {
    fun getCombosDetail(pageType: Int,id: Long): Observable<BaseResp<CommonDetailBean>>?

    fun getClothesSuggest(id: Long): Observable<BaseResp<MutableList<CommonDetailBean>>>?
}