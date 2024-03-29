package com.yizhipin.usercenter.api

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.News
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.usercenter.bean.Banner
import com.yizhipin.usercenter.data.api.Api
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by ${XiLei} on 2018/7/27.
 */
interface MainApi {
    /**
     * 获取banner
     */
    @GET(Api.BANNER)
    fun getBanner(): Observable<BaseResp<MutableList<Banner>>>

    /**
     * 获取热门商品
     */
    @GET(Api.HOT_GOODS_LIST)
    fun getGoodsList(@Query("hot") hot: Boolean): Observable<BaseResp<MutableList<Goods>>>

    /**
     * 获取图片地址
     */
    @GET(Api.IMAGE_ADDRESS)
    fun getOssAddress(): Observable<BaseResp<OssAddress>>

    @GET(Api.NEWS_LIST)
    fun getNews(@Query("currentPage") currentPage: String, @Query("uid") uid: String): Observable<BasePagingResp<MutableList<News>>>


    /**
     * 获取订单列表
     */
   /* @GET(Api.ORDER_LIST)
    fun getOrderList(): Observable<MutableList<ScheduleItemBean>>*/
}