package com.yizhipin.teacher.mine.setting.mvp

import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 */
class SystemSettingPresenterImpl @Inject constructor(val model: SystemSettingModelImpl, view: SystemSettingContract.IView) : BasePresenter<SystemSettingContract.IView>(view), SystemSettingContract.IPresenter {

}