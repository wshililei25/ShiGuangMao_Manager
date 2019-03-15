package com.qi.management.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.qi.management.R
import com.qi.management.injection.component.DaggerManagerComponent
import com.qi.management.injection.module.ManagerModule
import com.qi.management.presenter.StaffPresenter
import com.qi.management.presenter.view.StaffView
import com.qi.management.ui.adapter.StaffAdapter
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.startLoading
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.utils.AppPrefsUtils
import kotlinx.android.synthetic.main.activity_recyclerview.*
import org.jetbrains.anko.startActivity


/**
 * Created by ${XiLei} on 2018/8/23.
 * 员工列表
 */
class StaffListActivity : BaseMvpActivity<StaffPresenter>(), StaffView {

    private lateinit var mAdapter: StaffAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        initView()
        loadData()
    }

    private fun initView() {
        mHeaderBar.getTiTleTv().text = getString(R.string.staff_management)
        mRv.layoutManager = LinearLayoutManager(this)
        mAdapter = StaffAdapter(this)
        mRv.adapter = mAdapter
        mAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<UserInfo> {
            override fun onItemClick(item: UserInfo, position: Int) {
                startActivity<StaffDetailActivity>(BaseConstant.KEY_STAFF to item)
            }
        })
    }

    override fun injectComponent() {
        DaggerManagerComponent.builder().activityComponent(mActivityComponent).managerModule(ManagerModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        var map = mutableMapOf<String, String>()
        map.put("shopId", AppPrefsUtils.getString(BaseConstant.KEY_SHOP_ID))
        mBasePresenter.getStaffList(map)
    }

    override fun onGetStaffListSuccess(result: MutableList<UserInfo>) {

        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result != null) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

}



