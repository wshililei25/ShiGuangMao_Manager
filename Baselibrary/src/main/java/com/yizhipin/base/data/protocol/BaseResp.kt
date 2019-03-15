package com.yizhipin.base.data.protocol

/**
 * Created by ${XiLei} on 2018/7/27.
 */
open class BaseResp<out T>(open val code:String, open val msg:String, open val data:T)