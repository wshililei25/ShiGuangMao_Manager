package com.yizhipin.teacher.mine.password.mvp

import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 */
class PasswordModificationPresenterImpl @Inject constructor(val model: PasswordModificationModelImpl, view: PasswordModificationContract.IView) : BasePresenter<PasswordModificationContract.IView>(view), PasswordModificationContract.IPresenter {

}