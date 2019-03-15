package com.yizhipin.ui.activity

import android.os.Bundle
import com.yizhipin.R
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.FeeRecord
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.usercenter.bean.WorkStatusBean
import com.yizhipin.usercenter.injection.component.DaggerMainComponent
import com.yizhipin.usercenter.injection.module.MianModule
import com.yizhipin.usercenter.presenter.UserInfoPresenter
import com.yizhipin.usercenter.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_charging_setting.*

/**
 * Created by ${XiLei} on 2018/8/19.
 *收费设置
 */
class ChargeSetActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charging_setting)
    }

    override fun injectComponent() {
        DaggerMainComponent.builder().activityComponent(mActivityComponent).mianModule(MianModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onStart() {
        super.onStart()
        initData()
    }

    private fun initData() {
        var map = mutableMapOf<String, String>()
        map.put("id", AppPrefsUtils.getString(BaseConstant.KEY_SP_USER_ID))
        mBasePresenter.getUserInfo(map)
    }

    override fun getUserResult(result: UserInfo) {
        with(result) {
            mProfessionTv.text = position
            mPositionView.text = level
            mAmountTv.text = "¥ ${photoAmount}"
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