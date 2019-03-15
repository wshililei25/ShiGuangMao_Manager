package com.qi.management.store.wedding_photography.comboslist.presenter

import com.qi.management.bean.CommonDetailBean
import com.qi.management.store.wedding_photography.comboslist.CombosListContract
import com.qi.management.store.wedding_photography.comboslist.model.CombosListModelImpl
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.execute
import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.CodeListHandlerSubscriber
import javax.inject.Inject

@PerComponentScope
class CombosListPresenterImpl
@Inject constructor(view: CombosListContract.CombosListView, val model: CombosListModelImpl) : CombosListContract.CombosListPresenter, BasePresenter<CombosListContract.CombosListView>(view) {

    var currentPage = 0

    fun getCombosList(combosType: Int?, photoType: String) {
        model.getCombosList(combosType,photoType, currentPage + 1)?.execute(object : CodeListHandlerSubscriber<MutableList<CommonDetailBean>>(mView) {
            override fun onSucceed(data: BasePagingResp<MutableList<CommonDetailBean>>) {
                if (data.data.size == 0) return
                currentPage = data.pi.currentPage
                mView.add(data.data)
            }
        }, mLifecycleProvider)
    }

}