package com.yizhipin.usercenter.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.Store
import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.widgets.AddressPickerView
import com.yizhipin.provider.common.ProvideReqCode
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.injection.component.DaggerUserComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.presenter.TeacherEnterDatumPresenter
import com.yizhipin.usercenter.presenter.view.TeacherEnterDatumView
import kotlinx.android.synthetic.main.activity_teacher_enter_datum.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

/**
 * Created by ${XiLei} on 2018/9/24.
 * 老师入驻资料
 */
class TeacherEnterDatumActivity : BaseMvpActivity<TeacherEnterDatumPresenter>(), TeacherEnterDatumView, View.OnClickListener {

    private lateinit var mStore: Store

    private var mProvice = ""
    private var mCity = ""
    private var mDistrict = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_enter_datum)

        initView()
    }

    private fun initView() {
        mShopView.onClick(this)
        mAddressView.onClick(this)
        mBtn.onClick(this)
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.mShopView -> startActivityForResult<ShopActivity>(ProvideReqCode.CODE_REQ_SHOP)
            R.id.mAddressView -> {
                val popupWindow = PopupWindow(this)
                val rootView = LayoutInflater.from(this).inflate(R.layout.pop_address_picker, null, false)
                val addressView = rootView.findViewById<AddressPickerView>(R.id.apvAddress)
                addressView.setOnAddressPickerSure(object : AddressPickerView.OnAddressPickerSureListener {
                    override fun onSureClick(provice: String?, city: String?, district: String?, provinceCode: String?, cityCode: String?, districtCode: String?) {
                        mAddressTv.text = provice + city + district
                        mProvice = provice!!
                        mCity = city!!
                        mDistrict = district!!
                        popupWindow.dismiss()
                    }
                })
                popupWindow.setContentView(rootView)
                popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT)
                popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT)
                popupWindow.showAsDropDown(mHead)
            }
            R.id.mBtn -> {
                if (mShopTv.text.isNullOrEmpty() || mAddressTv.text.isNullOrEmpty() || mAddressDetailEv.text.isNullOrEmpty()
                        || mContactsEt.text.isNullOrEmpty() || mContactsMobileEt.text.isNullOrEmpty()
                        || mDeviceEt.text.isNullOrEmpty() || mIntroduceEt.text.isNullOrEmpty()) {
                    toast("内容输入不能为空")
                    return
                }

                var map = mutableMapOf<String, String>()
                map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_USER_ID))
                map.put("storeId", mStore.id)
                map.put("teacherType", mTeacherTypeEt.text.toString())
                map.put("applyType", mQuartersTypeEt.text.toString())
                map.put("provice", mProvice)
                map.put("city", mCity)
                map.put("area", mDistrict)
                map.put("detail", mAddressDetailEv.text.toString())
                map.put("emergencyContact", mContactsEt.text.toString())
                map.put("emergencyMobile", mContactsMobileEt.text.toString())
                map.put("device", mDeviceEt.text.toString())
                map.put("selfIntroduction", mIntroduceEt.text.toString())
                mBasePresenter.applyEnterDatum(map)
            }
        }
    }

    override fun applyEnterDatumSucccess(result: Teacher) {
        startActivity<TeacherWorksActivity>()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (resultCode) {
            ProvideReqCode.CODE_RESULT_SHOP -> {
                mStore = data!!.getParcelableExtra<Store>(BaseConstant.KEY_SHOP)
                mShopTv.setText(mStore.storeName)
            }
        }
    }
}