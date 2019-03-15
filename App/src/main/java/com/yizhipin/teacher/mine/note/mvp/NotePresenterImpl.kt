package com.yizhipin.teacher.mine.note.mvp

import com.yizhipin.base.mvp.presenter.BasePresenter
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 */
class NotePresenterImpl @Inject constructor(val model: NoteModelImpl, view: NoteContract.IView) : BasePresenter<NoteContract.IView>(view), NoteContract.IPresenter {

}