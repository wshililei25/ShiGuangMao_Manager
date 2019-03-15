package com.qi.management.store.production_management.presenter

import com.qi.management.bean.CommonDetailBean
import com.qi.management.bean.ProductionCategoryBean
import com.qi.management.store.production_management.model.ProductionListModelImpl
import com.qi.management.store.production_management.view.ProductionListView
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.execute
import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.CodeHandlerSubscriber
import com.yizhipin.base.rx.CodeListHandlerSubscriber
import javax.inject.Inject

@PerComponentScope
class ProductionListPresenterImpl @Inject constructor(view: ProductionListView, private val modle: ProductionListModelImpl) : ProductionListPresenter, BasePresenter<ProductionListView>(view) {

    private var currentPageIndex = 0

    var categoryID: Long? = null

    override fun getProductionCategory() {
        modle.getProductionCategory()?.execute(object : CodeHandlerSubscriber<MutableList<ProductionCategoryBean>>(mView) {
            override fun onSucceed(data: MutableList<ProductionCategoryBean>) {
                mView.showCategory(data)
            }

        }, mLifecycleProvider)
    }

    override fun getProductionList() {
        modle.getProductionList(null, null, categoryID, currentPageIndex + 1)?.execute(object : CodeListHandlerSubscriber<MutableList<CommonDetailBean>>(mView) {
            override fun onSucceed(data: BasePagingResp<MutableList<CommonDetailBean>>) {
                currentPageIndex = data.pi.currentPage
                mView.addList(data)
            }

        }, mLifecycleProvider)
    }


}