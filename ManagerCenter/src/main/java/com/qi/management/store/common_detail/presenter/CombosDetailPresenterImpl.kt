package com.qi.management.store.common_detail.presenter

import com.qi.management.bean.CommonDetailBean
import com.qi.management.store.common_detail.model.CombosDetailModelImpl
import com.qi.management.store.common_detail.view.CombosDetailView
import com.yizhipin.base.ext.execute
import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.CodeHandlerSubscriber
import javax.inject.Inject

@PerComponentScope
class CombosDetailPresenterImpl
@Inject constructor(view: CombosDetailView, private val model: CombosDetailModelImpl) : CombosDetailPresenter, BasePresenter<CombosDetailView>(view) {

    var bean: CommonDetailBean? = null
    var pageType: Int = 0

    override fun getDetail() {
        if (bean != null)
            model.getCombosDetail(pageType, bean!!.id)?.execute(object : CodeHandlerSubscriber<CommonDetailBean>(mView) {
                override fun onSucceed(data: CommonDetailBean) {
                    mView.show(data)
                }
            }, mLifecycleProvider)
    }

    override fun getSuggestion() {
        if (bean != null)
            model.getClothesSuggest(bean!!.id)?.execute(object : CodeHandlerSubscriber<MutableList<CommonDetailBean>>(mView) {
                override fun onSucceed(data: MutableList<CommonDetailBean>) {
                    mView.showSuggestion(data)
                }
            }, mLifecycleProvider)
    }
}