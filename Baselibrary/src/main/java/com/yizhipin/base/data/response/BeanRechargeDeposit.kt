package com.yizhipin.base.data.response

/**
 * Creator Qi
 * Date 2019/1/21
 * <br/>
 * 充值/补缴押金
 */
data class BeanRechargeDeposit(
        /**押金金额*/
        val amount: Double,
        /**退款时间*/
        val backTime: String,
        val id: Int,
        /**订单号*/
        val ordernum: String,
        /**是否支付成功*/
        val paySuccess: Boolean,
        val payTime: String,
        val payType: String,
        /**押金状态*/
        val status: Int,
        val uid: Int
)