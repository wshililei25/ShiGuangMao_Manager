package com.yizhipin.teacher.mine.cashpledge.mvp

import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.protocol.BaseResp
import com.yizhipin.base.data.response.BeanDeposit
import com.yizhipin.base.mvp.model.BaseModel
import com.yizhipin.usercenter.data.api.Api
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 * <br/>
 * 押金数据操作
 */
class CashPledgeModel @Inject constructor() : BaseModel {

    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun destroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getDepositByUID(uid: String): Observable<BaseResp<BeanDeposit>> {
        return RetrofitFactoryGet().create(Api::class.java).getDepositByUID(uid)
    }
}