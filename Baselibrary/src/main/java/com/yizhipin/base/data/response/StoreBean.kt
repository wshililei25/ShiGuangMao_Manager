package com.qi.management.bean

/**
 * Creator Qi
 * Date 2019/1/29
 */
class StoreBean(
        var attention: Boolean = false,
        var city: String = "",
        /***门店描述*/
        var content: String = "",
        var createTime: String = "",
        /***详细地址*/
        var detail: String = "",
        var hot: Boolean = false,
        /**门店id*/
        var id: String = "",
        var imgurl: String = "",
        /**纬度*/
        var lat: Double = 0.0,
        /**经度*/
        var lng: Double = 0.0,
        var loginUid: String = "",
        var province: String = "",
        var redMax: Int = 0,
        var redMin: Int = 0,
        var serviceCount: Int = 0,
        var starCount: Int = 0,
        var storeName: String = "",
        var tel: String = "",
        /**用户id*/
        var uid: String = ""
)
/*
{
    constructor() : this(false, "", "", "", "", false, "", "", 0.0, 0.0, "", "", 0, 0, 0, 0, "", "", 0)
}*/
