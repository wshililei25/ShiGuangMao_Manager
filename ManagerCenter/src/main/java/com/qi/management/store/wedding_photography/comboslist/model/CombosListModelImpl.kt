package com.qi.management.store.wedding_photography.comboslist.model

import com.qi.management.ApiService
import com.qi.management.bean.CommonDetailBean
import com.qi.management.store.wedding_photography.comboslist.CombosListContract
import com.yizhipin.base.data.net.RetrofitFactoryGet
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.usercenter.utils.UserPrefsUtils
import io.reactivex.Observable
import javax.inject.Inject

class CombosListModelImpl @Inject constructor() : CombosListContract.CombosListModel {
    override fun getCombosList(combosType: Int?, photoType: String,currentPage: Int): Observable<BasePagingResp<MutableList<CommonDetailBean>>>? {
        val userInfo = UserPrefsUtils.getUserInfo() ?: return null
        return RetrofitFactoryGet().create(ApiService::class.java).getCombosList(userInfo.id, userInfo.shopId, combosType,photoType, currentPage)
    }

}