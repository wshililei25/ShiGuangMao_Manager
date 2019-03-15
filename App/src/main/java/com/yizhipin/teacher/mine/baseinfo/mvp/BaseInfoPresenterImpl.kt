package com.yizhipin.teacher.mine.baseinfo.mvp

import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 */
class BaseInfoPresenterImpl @Inject constructor(val model: BaseInfoModelImpl, view: BaseInfoContract.BaseInfoView) : BasePresenter<BaseInfoContract.BaseInfoView>(view), BaseInfoContract.BaseInfoPresenter {

}