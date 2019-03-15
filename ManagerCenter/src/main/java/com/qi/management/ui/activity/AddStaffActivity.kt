package com.qi.management.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.qi.management.R
import com.qi.management.injection.component.DaggerManagerComponent
import com.qi.management.injection.module.ManagerModule
import com.qi.management.presenter.AddStaffPresenter
import com.qi.management.presenter.view.AddStaffView
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.provider.common.ProvideReqCode
import com.yizhipin.usercenter.data.response.StaffType
import kotlinx.android.synthetic.main.activity_add_staff.*
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

/**
 * Created by ${XiLei} on 2018/9/24.
 * 添加员工
 */
class AddStaffActivity : BaseMvpActivity<AddStaffPresenter>(), AddStaffView, View.OnClickListener {

    private lateinit var mStaffType: StaffType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_staff)

        initView()
    }

    private fun initView() {
        mJobView.onClick(this)
        mBtn.onClick(this)
    }

    override fun injectComponent() {
        DaggerManagerComponent.builder().activityComponent(mActivityComponent).managerModule(ManagerModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mJobView -> startActivityForResult<StaffTypeActivity>(ProvideReqCode.CODE_REQ_SHOP)
            R.id.mBtn -> {
                if (mMobileEt.text.isNullOrEmpty() || mJobEt.text.isNullOrEmpty() || mNameEt.text.isNullOrEmpty()) {
                    toast("内容输入不能为空")
                    return
                }

                var map = mutableMapOf<String, String>()
                map.put("shopId", AppPrefsUtils.getString(BaseConstant.KEY_SHOP_ID))
                map.put("mobile", mMobileEt.text.toString())
                map.put("position", mStaffType.code)
                map.put("nickname", mNameEt.text.toString())
                mBasePresenter.addStaff(map)
            }
        }
    }

    override fun onAddStaffSuccess(result: UserInfo) {
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            ProvideReqCode.CODE_RESULT_SHOP -> {
                mStaffType = data!!.getParcelableExtra<StaffType>(BaseConstant.KEY_SHOP)
                mJobEt.setText(mStaffType.positionName)
            }
        }
    }

}