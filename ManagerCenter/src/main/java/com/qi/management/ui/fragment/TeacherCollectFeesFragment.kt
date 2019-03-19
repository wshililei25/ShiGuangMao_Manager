package com.qi.management.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qi.management.R
import com.qi.management.injection.component.DaggerManagerComponent
import com.qi.management.injection.module.ManagerModule
import com.qi.management.presenter.TeacherPresenter
import com.qi.management.presenter.view.TeacherView
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_teacher_collect_fees.*

/**
 * Created by ${XiLei} on 2018/8/19.
 * 老师收费
 */
class TeacherCollectFeesFragment : BaseMvpFragment<TeacherPresenter>(), TeacherView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_teacher_collect_fees, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        mBasePresenter.getUserInfo()
    }

    override fun injectComponent() {
        DaggerManagerComponent.builder().activityComponent(mActivityComponent).managerModule(ManagerModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun getUserResult(result: UserInfo) {
        with(result) {

            mProfessionTv.text = position
            mPositionView.text = level
            mAmountTv.text = "¥ ${photoAmount}"
            mAmountRecordTv.text = "¥ ${photoAmount}"
        }
    }

    override fun onGetTeacherListSuccess(result: BasePagingResp<MutableList<Teacher>>) {
    }

    override fun onGetCameramanDetailsSuccess(result: Teacher) {
    }

}