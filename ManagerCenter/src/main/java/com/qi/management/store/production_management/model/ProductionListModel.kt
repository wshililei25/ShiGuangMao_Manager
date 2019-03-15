package com.qi.management.store.production_management.model

import com.qi.management.bean.CommonDetailBean
import com.qi.management.bean.ProductionCategoryBean
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.mvp.model.BaseModel
import io.reactivex.Observable

/**
 * Creator Qi
 * Date 2019/2/26
 */
interface ProductionListModel : BaseModel {
    /**
     *  获取产品分类
     */
    fun getProductionCategory(): Observable<BaseResp<MutableList<ProductionCategoryBean>>>?

    /***
     * 获取产品分页
     */
    fun getProductionList(loginUid: String?, storeId: String?, catagory: Long?, currentPage: Int): Observable<BasePagingResp<MutableList<CommonDetailBean>>>?
}