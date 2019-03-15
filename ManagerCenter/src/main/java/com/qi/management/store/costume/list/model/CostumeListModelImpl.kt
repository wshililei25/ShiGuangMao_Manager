package com.qi.management.store.costume.list.model

import com.qi.management.ApiService
import com.qi.management.bean.CommonDetailBean
import com.qi.management.bean.CostumeCategoryBean
import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.injection.PerComponentScope
import io.reactivex.Observable
import javax.inject.Inject

@PerComponentScope
class CostumeListModelImpl @Inject constructor() : CostumeListModel {
    override fun getCostumeList(category: Int, currentPage: Int, sellType: Int, sex: Int): Observable<BasePagingResp<MutableList<CommonDetailBean>>>? {
        return RetrofitFactoryGet().create(ApiService::class.java).getCostumeList(category, currentPage, sellType, sex)
    }

    override fun getCostumeCategoryList(sex: Int, sellType: Int): Observable<BaseResp<List<CostumeCategoryBean>>> {
        return RetrofitFactoryGet().create(ApiService::class.java).getCostumeCategoryList(sex, sellType)
    }

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}