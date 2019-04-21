package com.yizhipin.usercenter.data.repository

import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.News
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.Teacher
import com.yizhipin.usercenter.api.MainApi
import com.yizhipin.usercenter.bean.Banner
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/27.
 */
class MainRepository @Inject constructor() {

    /**
     * 获取用户信息
     */
    fun getBanner(): Observable<BaseResp<MutableList<Banner>>> {
        return RetrofitFactoryGet().create(MainApi::class.java)
                .getBanner()
    }

    fun getGoodsList(): Observable<BaseResp<MutableList<Goods>>> {
        return RetrofitFactoryGet().create(MainApi::class.java)
                .getGoodsList(true)
    }

    fun getOssAddress(): Observable<BaseResp<OssAddress>> {
        return RetrofitFactoryGet().create(MainApi::class.java)
                .getOssAddress()
    }
    fun getNews(map: MutableMap<String, String>): Observable<BasePagingResp<MutableList<News>>> {
        return RetrofitFactoryGet().create(MainApi::class.java).getNews(map["currentPage"]!!, map["uid"]!!)
    }

    /*   fun getOrderList(): Observable<MutableList<ScheduleItemBean>> {
           return RetrofitFactoryGet.create(MainApi::class.java).getOrderList()
       }*/

}
