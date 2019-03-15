package com.qi.management.presenter.view

import android.app.Activity
import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.mvp.view.BaseView
import com.yizhipin.usercenter.bean.WorkStatusBean

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface TeacherDatumView : BaseView {
    fun onGetTeacherDatumSuccess(result: Teacher)
}