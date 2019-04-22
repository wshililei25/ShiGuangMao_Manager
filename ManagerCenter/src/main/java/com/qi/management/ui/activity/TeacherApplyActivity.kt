package com.qi.management.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import com.qi.management.R
import com.qi.management.injection.component.DaggerManagerComponent
import com.qi.management.injection.module.ManagerModule
import com.qi.management.presenter.TeacherApplyPresenter
import com.qi.management.presenter.view.TeacherApplyView
import com.qi.management.ui.adapter.TeacherApplyAdapter
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.event.TeacherApplyEvent
import com.yizhipin.base.ext.startLoading
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import kotlinx.android.synthetic.main.activity_recyclerview.*


/**
 * Created by ${XiLei} on 2018/8/23.
 * 老师入驻申请列表
 */
class TeacherApplyActivity : BaseMvpActivity<TeacherApplyPresenter>(), TeacherApplyView {

    private lateinit var mAdapter: TeacherApplyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        initView()
        loadData()
        initObserve()
    }

    private fun initView() {
        mHeaderBar.getTiTleTv().text = getString(R.string.teacher_apply)
        mRv.layoutManager = LinearLayoutManager(this)
        mAdapter = TeacherApplyAdapter(this)
        mRv.adapter = mAdapter
    }

    override fun injectComponent() {
        DaggerManagerComponent.builder().activityComponent(mActivityComponent).managerModule(ManagerModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        var map = mutableMapOf<String, String>()
        map.put("storeId", AppPrefsUtils.getString(BaseConstant.KEY_SHOP_ID))
        mBasePresenter.getTeacherApplyList(map)
    }

    override fun onGetTeacherApplyListSuccess(result: MutableList<Teacher>) {

        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result != null) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    private fun initObserve() {
        Bus.observe<TeacherApplyEvent>()
                .subscribe { t: TeacherApplyEvent ->
                    run {
                        var map = mutableMapOf<String, String>()
                        map.put("auditUid", AppPrefsUtils.getString(BaseConstant.KEY_SP_USER_ID))
                        map.put("uid", t.teacherUid)
                        map.put("status", "1")
                        mBasePresenter.approveTeacherApply(map)
                    }
                }.registerInBus(this)
    }

    override fun onApproveTeacherApplySuccess(result: Boolean) {
        loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}



