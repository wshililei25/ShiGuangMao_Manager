package com.yizhipin.usercenter.presenter.view

import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.mvp.view.BaseView

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface TeacherEnterDatumView : BaseView {
    fun applyEnterDatumSucccess(result: Teacher)
}