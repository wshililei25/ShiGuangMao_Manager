package com.qi.management.store.costume.list.presenter

import com.qi.management.bean.CommonDetailBean
import com.qi.management.bean.CostumeCategoryBean
import com.qi.management.store.costume.list.model.CostumeListModelImpl
import com.qi.management.store.costume.list.view.CostumeListView
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.ext.execute
import com.yizhipin.base.injection.PerComponentScope
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.mvp.view.ListView
import com.yizhipin.base.rx.CodeHandlerSubscriber
import com.yizhipin.base.rx.CodeListHandlerSubscriber
import javax.inject.Inject

@PerComponentScope
class CostumeListPresenterImpl @Inject constructor(view: CostumeListView, private val mModel: CostumeListModelImpl) : CostumeListPresenter, BasePresenter<CostumeListView>(view) {

    private var currentPage: Int = 0

    /**
     * 场馆类型
     */
    var sellType: Int = 0
    /**
     * 性别
     */
    var sex: Int = 0
    /**
     * 服装类型
     */
    var category: Int = 0

    override fun getCostumeCategoryList() {
        mModel.getCostumeCategoryList(sex, sellType).execute(object : CodeHandlerSubscriber<List<CostumeCategoryBean>>(mView) {
            override fun onSucceed(data: List<CostumeCategoryBean>) {
                mView.showCategory(data)
            }

        }, mLifecycleProvider)
    }

    override fun getCostumeList() {
        mModel.getCostumeList(category, currentPage + 1, sellType, sex)?.execute(object : CodeListHandlerSubscriber<MutableList<CommonDetailBean>>(mView) {
            override fun onSucceed(data: BasePagingResp<MutableList<CommonDetailBean>>) {
                if (data.data.size > 0) {
                    currentPage = data.pi.currentPage
                    (mView as ListView<CommonDetailBean>).add(data.data)
                }
            }

        }, mLifecycleProvider)
    }
}