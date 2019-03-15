package com.qi.management.store.production_management.model

import com.qi.management.ApiService
import com.qi.management.bean.CommonDetailBean
import com.qi.management.bean.ProductionCategoryBean
import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.injection.PerComponentScope
import io.reactivex.Observable
import javax.inject.Inject

@PerComponentScope
class ProductionListModelImpl @Inject constructor() : ProductionListModel {

    override fun getProductionCategory(): Observable<BaseResp<MutableList<ProductionCategoryBean>>>? {
        return RetrofitFactoryGet().create(ApiService::class.java).getProductionCategory()
    }

    override fun getProductionList(loginUid: String?, storeId: String?, catagory: Long?, currentPage: Int): Observable<BasePagingResp<MutableList<CommonDetailBean>>>? {
        return RetrofitFactoryGet().create(ApiService::class.java).getProductionList(loginUid,storeId,catagory,currentPage)
}

    override fun start() {

    }

    override fun destroy() {

    }


}