package com.yizhipin.teacher.mine.baseinfo.mvp

import com.yizhipin.base.mvp.model.BaseModel
import com.yizhipin.base.mvp.view.BaseView

/**
 * Creator Qi
 * Date 2019/1/4
 */
interface BaseInfoContract {
    interface BaseInfoModel : BaseModel
    interface BaseInfoPresenter
    interface BaseInfoView : BaseView

}