package com.qi.management.presenter.view

import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.mvp.view.BaseView

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface TeacherApplyView : BaseView {
    fun onGetTeacherApplyListSuccess(result: MutableList<Teacher>)
    fun onApproveTeacherApplySuccess(result: Boolean)
}