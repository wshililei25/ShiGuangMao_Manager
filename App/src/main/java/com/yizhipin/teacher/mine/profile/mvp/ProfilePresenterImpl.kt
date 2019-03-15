package com.yizhipin.teacher.mine.profile.mvp

import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 */
class ProfilePresenterImpl @Inject constructor(val model: ProfileModelImpl, view: ProfileContract.IView) : BasePresenter<ProfileContract.IView>(view), ProfileContract.IPresenter {

}