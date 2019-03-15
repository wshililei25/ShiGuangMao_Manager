package com.yizhipin.teacher.mine.attention.user.mvp

import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 */
class UserPresenterImpl @Inject constructor(val model: UserModelImpl, view: UserContract.IView) : BasePresenter<UserContract.IView>(view), UserContract.IPresenter {

}