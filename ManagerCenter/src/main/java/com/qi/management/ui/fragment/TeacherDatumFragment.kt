package com.qi.management.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qi.management.R
import com.qi.management.injection.component.DaggerManagerComponent
import com.qi.management.injection.module.ManagerModule
import com.qi.management.presenter.TeacherDatumPresenter
import com.qi.management.presenter.view.TeacherDatumView
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.base.utils.AppPrefsUtils
import kotlinx.android.synthetic.main.fragment_teacher_datum.*

/**
 * Created by ${XiLei} on 2018/8/19.
 * 老师资料
 */
class TeacherDatumFragment : BaseMvpFragment<TeacherDatumPresenter>(), TeacherDatumView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_teacher_datum, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }

    private fun initData() {
        var map = mutableMapOf<String, String>()
        map.put("uid", if (arguments!!.getString(BaseConstant.KEY_TEACHER_UID, "").isNullOrEmpty()) AppPrefsUtils.getString(BaseConstant.KEY_SP_USER_ID) else arguments!!.getString(BaseConstant.KEY_TEACHER_UID, ""))
        mBasePresenter.getTeacherDatum(map)
    }

    override fun injectComponent() {
        DaggerManagerComponent.builder().activityComponent(mActivityComponent).managerModule(ManagerModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onGetTeacherDatumSuccess(result: Teacher) {
        with(result) {

            mTeacherTypeEt.text = teacherType
            mQuartersTypeEt.text = applyType
            mAddressTv.text = provice + city + area
            mAddressDetailEv.text = detail
            mContactsEt.text = emergencyContact
            mContactsMobileEt.text = emergencyMobile
            mDeviceEt.text = device
            mIntroduceEt.text = selfIntroduction

            store?.let {
                mShopTv.text = store.storeName
            }
        }
    }
}