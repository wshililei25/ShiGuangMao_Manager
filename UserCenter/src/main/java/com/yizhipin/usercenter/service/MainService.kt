package com.yizhipin.usercenter.service

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.News
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.usercenter.bean.Banner
import io.reactivex.Observable


/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface MainService {

    fun getBanner(): Observable<MutableList<Banner>>
    fun getGoodsList(): Observable<MutableList<Goods>>
    fun getOssAddress(): Observable<OssAddress>
    fun getNews(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<News>>>
    //    fun getOrderList():Observable<MutableList<ScheduleItemBean>>

}
