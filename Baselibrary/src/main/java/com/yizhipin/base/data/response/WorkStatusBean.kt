package com.yizhipin.usercenter.bean

/**
 * Creator Qi
 * Date 2019/1/13
 * 工作状态
 */
data class WorkStatusBean(
        /**上下班记录时间*/
        val createTime: String,
        val id: Int,
        val uid: Int,
        /**是否上班*/
        val work: Boolean
)