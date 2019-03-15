package com.qi.management.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kennyc.view.MultiStateView
import com.qi.management.R
import com.qi.management.injection.component.DaggerManagerComponent
import com.qi.management.injection.module.ManagerModule
import com.qi.management.presenter.StaffDetailsPresenter
import com.qi.management.presenter.view.StaffDetailsView
import com.qi.management.ui.adapter.ManagerOrderAdapter
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.ManagerOrder
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_staff_details.*

/**
 * Created by ${XiLei} on 2018/9/22.
 * 员工详情
 */
class StaffDetailActivity : BaseMvpActivity<StaffDetailsPresenter>(), StaffDetailsView, View.OnClickListener {

    private lateinit var mUserInfo: UserInfo
    private lateinit var mAdapter: ManagerOrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_staff_details)

        initView()
        initData()
    }

    override fun injectComponent() {
        DaggerManagerComponent.builder().activityComponent(mActivityComponent).managerModule(ManagerModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun initView() {

        mUserInfo = intent.getParcelableExtra<UserInfo>(BaseConstant.KEY_STAFF)
        with(mUserInfo) {
            mUserIv.loadUrl(imgurl)
            mStatusTv.text = if (work) {
                "上班中"
            } else {
                "下班中"
            }
            mNameTv.text = nickname + " " + teacherType
            mMobileTv.text = realName + "  " + mobile
        }

        mBackIv.onClick(this)

        mRv.layoutManager = LinearLayoutManager(this)
        mAdapter = ManagerOrderAdapter(this)
        mRv.adapter = mAdapter
    }

    private fun initData() {
        var map = mutableMapOf<String, String>()
        map.put("uid", mUserInfo.id)
        mBasePresenter.getStaffOrder(map)
    }

    override fun onGetStaffDetailsSuccess(result: MutableList<ManagerOrder>) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result != null) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mBackIv -> finish()
        }
    }


}