package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.OssAddress
import com.yizhipin.base.data.response.Works
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.injection.component.DaggerUserComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.presenter.TeacherWorkPresenter
import com.yizhipin.usercenter.presenter.view.TeacherWorkView
import com.yizhipin.usercenter.ui.adapter.TeacherWorkAdapter
import kotlinx.android.synthetic.main.activity_teacher_works.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/9/24.
 * 上传作品
 */

class TeacherWorksActivity : BaseMvpActivity<TeacherWorkPresenter>(), TeacherWorkView, View.OnClickListener {

    private lateinit var mAdapter: TeacherWorkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_works)

        initView()
    }

    private fun initView() {

        mBtn.onClick(this)
        mRv.layoutManager = LinearLayoutManager(this)
        mAdapter = TeacherWorkAdapter(this)
        mRv.adapter = mAdapter

        mHeaderBar.getRightTv().onClick {
            startActivity<TeacherApplySuccessActivity>()
            finish()
        }
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onStart() {
        super.onStart()
        initData()
    }

    private fun initData() {
        var map = mutableMapOf<String, String>()
        map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_USER_ID))
        mBasePresenter.getWorksList(map)
    }


    override fun onGetWorkListSuccess(result: MutableList<Works>) {
        mAdapter.setData(result)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mBtn -> {
                startActivity<TeacherWorkAddActivity>()
            }
        }
    }

    override fun onGetOssSignSuccess(result: String) {
    }

    override fun onGetOssSignFileSuccess(result: String) {
    }

    override fun onGetOssAddressSuccess(result: OssAddress) {
    }

    override fun onAddWorkSuccess(result: Works) {
    }

}