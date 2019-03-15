package com.qi.management.presenter.view

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.mvp.view.BaseView

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface TeacherView : BaseView {
    fun onGetTeacherListSuccess(result: BasePagingResp<MutableList<Teacher>>)
    fun onGetCameramanDetailsSuccess(result: Teacher)
}