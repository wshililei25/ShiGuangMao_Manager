package com.yizhipin.teacher.mine.chargingsetting.mvp

import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 */
class ChargingSettingPresenterImpl @Inject constructor(val model: ChargingSettingModelImpl, view: ChargingSettingContract.IView) : BasePresenter<ChargingSettingContract.IView>(view), ChargingSettingContract.IPresenter {

}