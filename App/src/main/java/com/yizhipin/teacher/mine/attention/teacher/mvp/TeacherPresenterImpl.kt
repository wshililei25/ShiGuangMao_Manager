package com.yizhipin.teacher.mine.attention.teacher.mvp

import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 */
class TeacherPresenterImpl @Inject constructor(val model: TeacherModelImpl, view: TeacherContract.IView) : BasePresenter<TeacherContract.IView>(view), TeacherContract.IPresenter {

}