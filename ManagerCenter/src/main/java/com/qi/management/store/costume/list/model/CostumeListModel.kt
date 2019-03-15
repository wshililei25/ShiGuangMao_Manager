package com.qi.management.store.costume.list.model

import com.qi.management.bean.CommonDetailBean
import com.qi.management.bean.CostumeCategoryBean
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.mvp.model.BaseModel
import io.reactivex.Observable

/**
 * Creator Qi
 * Date 2019/2/28
 */
interface CostumeListModel : BaseModel {
    /**
     * 获取服装类型
     * @param sex 性别0，男性；1，女性
     * @param sellType 场馆类型
     */
    fun getCostumeCategoryList(sex: Int, sellType: Int): Observable<BaseResp<List<CostumeCategoryBean>>>

    /**
     * 获取服装分页列表
     * @param category 类型
     * @param currentPage 页面
     * @param sellType 场馆类型
     * @param sex 性别
     */
    fun getCostumeList(category: Int, currentPage: Int, sellType: Int, sex: Int): Observable<BasePagingResp<MutableList<CommonDetailBean>>>?
}