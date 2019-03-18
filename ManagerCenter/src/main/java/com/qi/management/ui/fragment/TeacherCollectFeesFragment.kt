package com.qi.management.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qi.management.R
import com.qi.management.injection.component.DaggerManagerComponent
import com.qi.management.injection.module.ManagerModule
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.FeeRecord
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.usercenter.bean.WorkStatusBean
import com.yizhipin.usercenter.presenter.UserInfoPresenter
import com.yizhipin.usercenter.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.fragment_teacher_collect_fees.*

/**
 * Created by ${XiLei} on 2018/8/19.
 * 老师收费
 */
class TeacherCollectFeesFragment : BaseMvpFragment<UserInfoPresenter>(), UserInfoView {

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

    override fun onEditUserResult(result: UserInfo) {
    }

    override fun onGetCartSuccess(result: Int) {
    }

    override fun showWorkStatus(workStatusBean: WorkStatusBean) {
    }

    override fun getFeeRecordListSuccess(result: MutableList<FeeRecord>) {
    }

    override fun onGetOssSignSuccess(result: String) {
    }

    override fun onGetOssSignFileSuccess(result: String) {
    }

    override fun onGetOssAddressSuccess(result: OssAddress) {
    }
}