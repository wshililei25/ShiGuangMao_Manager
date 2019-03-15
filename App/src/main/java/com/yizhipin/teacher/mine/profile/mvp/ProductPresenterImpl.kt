package com.yizhipin.teacher.mine.profile.mvp

import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2019/1/9
 */
class ProductPresenterImpl @Inject constructor(val model: ProductModelImpl, val view: ProductContract.IView) : ProductContract.IPresenter, BasePresenter<ProductContract.IView>()