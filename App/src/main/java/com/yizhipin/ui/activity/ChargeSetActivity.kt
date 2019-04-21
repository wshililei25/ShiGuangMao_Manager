package com.yizhipin.ui.activity

import android.os.Bundle
import android.view.View
import com.yizhipin.R
import com.yizhipin.base.data.response.FeeRecord
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
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
class ChargeSetActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView, View.OnClickListener {
    private var mAmount = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charging_setting)
        initView()
    }

    override fun injectComponent() {
        DaggerMainComponent.builder().activityComponent(mActivityComponent).mianModule(MianModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun initView() {
        mLeftTv.onClick(this)
        mEightTv.onClick(this)
        mBtn.onClick(this)
    }

    override fun onStart() {
        super.onStart()
        initData()
    }

    private fun initData() {
        mBasePresenter.getUserInfo()
    }

    override fun getUserResult(result: UserInfo) {
        with(result) {
            mProfessionTv.text = position
            mPositionView.text = level
            mAmountTv.text = "¥ ${photoAmount}"

            if (photoAmount.toDouble() > 0) {
                mLeftTv.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_circle_solid_white_border_red))
                mLeftTv.setTextColor(resources.getColor(R.color.yRed))
                mEightTv.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_circle_solid_red))
                mEightTv.setTextColor(resources.getColor(R.color.yWhite))
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mLeftTv -> {
                mAmount = 0.00
                mAmountTv.text = "¥ 0.0"
                mLeftTv.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_circle_solid_red))
                mLeftTv.setTextColor(resources.getColor(R.color.yWhite))
                mEightTv.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_circle_solid_white_border_red))
                mEightTv.setTextColor(resources.getColor(R.color.yRed))
            }
            R.id.mEightTv -> {
                mAmount = 88.00
                mAmountTv.text = "¥ 88.0"
                mLeftTv.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_circle_solid_white_border_red))
                mLeftTv.setTextColor(resources.getColor(R.color.yRed))
                mEightTv.setBackgroundDrawable(resources.getDrawable(R.drawable.bg_circle_solid_red))
                mEightTv.setTextColor(resources.getColor(R.color.yWhite))
            }
            R.id.mBtn -> {
                var map = mutableMapOf<String, String>()
                map.put("photoAmount", mAmount.toString())
                mBasePresenter.editUserInfo(map)
            }
        }
    }

    override fun onEditUserResult(result: UserInfo) {
        finish()
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

    override fun getUnReadNewCount(result: Int) {
    }
}