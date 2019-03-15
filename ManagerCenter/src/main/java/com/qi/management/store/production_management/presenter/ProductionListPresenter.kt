package com.qi.management.store.production_management.presenter

/**
 * 产品列表Presenter
 * Creator Qi
 * Date 2019/2/26
 */
interface ProductionListPresenter{
    /**
     * 获取产品分类
     */
    fun getProductionCategory()

    /***
     * 获取产品分页
     */
    fun getProductionList()

}