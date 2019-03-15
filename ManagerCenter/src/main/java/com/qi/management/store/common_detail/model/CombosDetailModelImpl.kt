package com.qi.management.store.common_detail.model

import com.qi.management.ApiService
import com.qi.management.bean.CommonDetailBean
import com.qi.management.store.common_detail.view.CommonDetailActivity
import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.injection.PerComponentScope
import io.reactivex.Observable
import javax.inject.Inject

@PerComponentScope
class CombosDetailModelImpl @Inject constructor() : CombosDetailModel {
    override fun getClothesSuggest(id: Long): Observable<BaseResp<MutableList<CommonDetailBean>>>? {
        return RetrofitFactoryGet().create(ApiService::class.java).getClothesSuggest(id)
    }

    override fun getCombosDetail(pageType: Int, id: Long): Observable<BaseResp<CommonDetailBean>>? {
        val service = RetrofitFactoryGet().create(ApiService::class.java)
        return when (pageType) {
            CommonDetailActivity.PageType.Combos.ordinal -> service.getCombosDetail(id)
            CommonDetailActivity.PageType.Costume.ordinal -> service.getClothDetail(id)
            CommonDetailActivity.PageType.Production.ordinal -> service.getProductionDetail(id)
            else -> null
        }
    }

    override fun start() {
    }

    override fun destroy() {
    }
}