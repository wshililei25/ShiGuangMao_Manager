package com.qi.management.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.qi.management.R
import com.qi.management.injection.component.DaggerManagerComponent
import com.qi.management.injection.module.ManagerModule
import com.qi.management.presenter.TeacherPresenter
import com.qi.management.presenter.view.TeacherView
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.protocol.BasePagingResp
import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.goods.ui.adapter.TeacherVpAdapter
import kotlinx.android.synthetic.main.activity_teacher_details.*

/**
 * Created by ${XiLei} on 2018/9/22.
 * 老师详情
 */
class TeacherDetailActivity : BaseMvpActivity<TeacherPresenter>(), TeacherView, View.OnClickListener {

    @Autowired(name = BaseConstant.KEY_TEACHER_ID)
    @JvmField
    var mTeacherId: String = "" //老师id

    @Autowired(name = BaseConstant.KEY_TEACHER_UID)
    @JvmField
    var mTeacherUid: String = "" //老师用户id

    private lateinit var mSetMealDetails: Teacher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_details)

        initView()
        loadGoodDetailsData()
    }

    override fun injectComponent() {
        DaggerManagerComponent.builder().activityComponent(mActivityComponent).managerModule(ManagerModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun initView() {
        mBackIv.onClick(this)

        mTab.tabMode = TabLayout.MODE_FIXED
        var mVpAdapter = TeacherVpAdapter(supportFragmentManager, mTeacherUid)
        mVp.adapter = mVpAdapter
        mTab.setupWithViewPager(mVp)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mBackIv -> finish()
        }
    }

    /**
     * 老师详情
     */
    private fun loadGoodDetailsData() {
        var map = mutableMapOf<String, String>()
        map.put("id", mTeacherId)
        mBasePresenter.getCameramanDetails(map)
    }

    /**
     * 获取老师详情成功
     */
    override fun onGetCameramanDetailsSuccess(result: Teacher) {
        mSetMealDetails = result
        with(result) {

            mUserIv.loadUrl(webUser.imgurl)
            mStatusTv.text = if (webUser.work) {
                "上班中"
            } else {
                "下班中"
            }
            mNameTv.text = webUser.nickname + " " + teacherType + " | " + applyType
            mMobileTv.text = webUser.realName + "  " + webUser.mobile
        }
    }

    override fun onGetTeacherListSuccess(result: BasePagingResp<MutableList<Teacher>>) {
    }
}