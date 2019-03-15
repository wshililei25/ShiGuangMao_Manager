package com.yizhipin.usercenter.service.impl

import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.ext.convert
import com.yizhipin.usercenter.bean.Banner
import com.yizhipin.usercenter.data.repository.MainRepository
import com.yizhipin.usercenter.service.MainService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by ${XiLei} on 2018/7/26.
 */
open class MainServiceImpl @Inject constructor() : MainService {

    @Inject
    lateinit var mRepository: MainRepository

    override fun getBanner(): Observable<MutableList<Banner>> {
        return mRepository.getBanner()
                .convert()
    }

    override fun getGoodsList(): Observable<MutableList<Goods>> {

        return mRepository.getGoodsList().convert()
    }

    override fun getOssAddress(): Observable<OssAddress> {

        return mRepository.getOssAddress().convert()
    }

    override fun getTeacherDatum(map: MutableMap<String, String>): Observable<Teacher> {
        return mRepository.getTeacherDatum(map).convert()
    }

    /**
     * 抢单获取订单列表
     */
/*    override fun getOrderList(): Observable<MutableList<ScheduleItemBean>> {
        return mRepository.getOrderList().convertPaging()
    }*/
}