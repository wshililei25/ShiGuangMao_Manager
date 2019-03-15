package com.qi.management.presenter.view

import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.TeacherWorks
import com.yizhipin.base.mvp.view.BaseView

/**
 * Created by ${XiLei} on 2018/7/26.
 */
interface TeacherEvaluateView : BaseView {
    fun onGetTeacherWorksSuccess(result: BasePagingResp<MutableList<TeacherWorks>>)
}