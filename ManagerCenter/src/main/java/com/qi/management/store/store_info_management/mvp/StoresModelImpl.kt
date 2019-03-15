package com.qi.management.store.store_info_management.mvp

import com.qi.management.ApiService
import com.qi.management.bean.StoreBean
import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.net.RetrofitFactoryPut
import com.yizhipin.base.data.protocol.BaseResp
import io.reactivex.Observable
import javax.inject.Inject

/**
 * 门店信息
 * Creator Qi
 * Date 2018/12/30
 */
class StoresModelImpl @Inject constructor() : StoresContract.IModel {

    var storeBean = StoreBean()

    override fun start() {

    }

    override fun destroy() {

    }

    fun getStore(shopID: String): Observable<BaseResp<StoreBean>> {
        return RetrofitFactoryGet().create(ApiService::class.java).getStore(shopID)
    }

    fun saveStoreInfo(storeBean: StoreBean): Observable<BaseResp<StoreBean>> {
        val map = mutableMapOf<String, String>()
        map["uid"] = "${storeBean.uid}"
        map["imgurl"] = storeBean.imgurl
        map["storeName"] = storeBean.storeName
        map["province"] = storeBean.province
        map["city"] = storeBean.city
        map["detail"] = storeBean.detail
        map["content"] = storeBean.content
        map["lng"] = "${storeBean.lng}"
        map["lat"] = "${storeBean.lat}"
        map["id"] = storeBean.id
        return RetrofitFactoryPut(map).create(ApiService::class.java).saveStoreInfo(storeBean.id)
    }
}