package com.qi.management

import com.qi.management.bean.CommonDetailBean
import com.qi.management.bean.CostumeCategoryBean
import com.qi.management.bean.ProductionCategoryBean
import com.qi.management.bean.StoreBean
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import io.reactivex.Observable
import retrofit2.http.*

/**
 * 管理层模块接口
 * Creator Qi
 * Date 2019/1/29
 */
interface ApiService {
    /**
     * 获取门店信息
     * @param id 门店id，用户信息的shopId就是门店id
     */
    @GET("api/Store/{id}")
    fun getStore(@Path("id") id: String): Observable<BaseResp<StoreBean>>

    /**
     * 更新门店信息
     */
    @PUT("api/Store/{id}")
    fun updateStore(@Path("id") id: String): Observable<BaseResp<StoreBean>>

    /**
     * 删除门店
     */
    @DELETE("api/Store/{id}")
    fun deleteStore(@Path("id") id: String): Observable<BaseResp<StoreBean>>

    /**
     * 修改门店信息
     */
    @PUT("api/Store/{id}")
    fun saveStoreInfo(@Path("id") id: String): Observable<BaseResp<StoreBean>>

    /**
     * 获取套餐分页列表
     * @param loginUid 当前登录用户ID
     * @param storeId 门店id
     * @param combosType 套餐类型(0套餐,1私人定制)
     * @param currentPage 当前分页数
     */
    @GET("api/PhotoPackage/Page")
    fun getCombosList(@Query("loginUid") loginUid: String, @Query("storeId") storeId: String, @Query("packageType") combosType: Int?, @Query("type") photoType: String, @Query("currentPage") currentPage: Int): Observable<BasePagingResp<MutableList<CommonDetailBean>>>

    /**
     * 获取详情
     * @param id 套餐id
     */
    @GET("api/PhotoPackage/{id}")
    fun getCombosDetail(@Path("id") id: Long): Observable<BaseResp<CommonDetailBean>>

    /**
     * 获取产品分类
     */
    @GET("api/MarkerProductCatagory/List")
    fun getProductionCategory(): Observable<BaseResp<MutableList<ProductionCategoryBean>>>

    /**
     * 获取时光超市产品列表(分页)
     * @param loginUid 当前登录用户ID
     * @param storeId 门店id
     * @param catagory 分类ID
     * @param currentPage 当前分页数
     */
    @GET("api/MarkerProduct/Page")
    fun getProductionList(@Query("loginUid") loginUid: String?, @Query("storeId") storeId: String?, @Query("catagory") catagory: Long?, @Query("currentPage") currentPage: Int): Observable<BasePagingResp<MutableList<CommonDetailBean>>>?

    /**
     * 获取服装类型
     * @param sex 性别0，男性；1，女性
     * @param sellType 场馆类型
     */
    @GET("api/ClothesCatagory/List")
    fun getCostumeCategoryList(@Query("sex") sex: Int, @Query("type") sellType: Int): Observable<BaseResp<List<CostumeCategoryBean>>>

    /**
     * 获取服装分页列表
     * @param category 类型
     * @param currentPage 页面
     * @param sellType 场馆类型
     * @param sex 性别
     */
    @GET("api/Clothes/Page")
    fun getCostumeList(@Query("catagory") category: Int, @Query("currentPage") currentPage: Int, @Query("SellType") sellType: Int, @Query("sex") sex: Int): Observable<BasePagingResp<MutableList<CommonDetailBean>>>?

    /**
     * 服装推荐
     * @param clothID 服装id
     */
    @GET("api/Clothes/Suggest/{id}")
    fun getClothesSuggest(@Path("id") clothID: Long): Observable<BaseResp<MutableList<CommonDetailBean>>>?

    @GET("api/Clothes/{id}")
    fun getClothDetail(@Path("id") id: Long): Observable<BaseResp<CommonDetailBean>>?

    /**
     * 获取产品详情
     */
    @GET("api/MarkerProduct/{id}")
    fun getProductionDetail(@Path("id") id: Long): Observable<BaseResp<CommonDetailBean>>?
}