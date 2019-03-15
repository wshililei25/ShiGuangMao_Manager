package com.yizhipin.teacher.mine.phone.mvp

import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 */
class PhonePresenterImpl @Inject constructor(val model: PhoneModelImpl, view: PhoneContract.IView) : BasePresenter<PhoneContract.IView>(view), PhoneContract.IPresenter {

}