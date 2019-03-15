package com.qi.management.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.qi.management.R
import com.qi.management.injection.component.DaggerManagerComponent
import com.qi.management.injection.module.ManagerModule
import com.qi.management.presenter.StaffTypePresenter
import com.qi.management.presenter.view.StaffTypeView
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.ui.adapter.StaffTypeAdapter
import com.yizhipin.provider.common.ProvideReqCode
import com.yizhipin.usercenter.data.response.StaffType
import kotlinx.android.synthetic.main.activity_recyclerview.*

/**
 * Created by ${XiLei} on 2018/9/24.
 * 员工角色
 */

class StaffTypeActivity : BaseMvpActivity<StaffTypePresenter>(), StaffTypeView {

    private lateinit var mShopAdapter: StaffTypeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)

        initView()
    }

    private fun initView() {
        mHeaderBar.getTiTleTv().text = "员工角色"
        mRv.layoutManager = LinearLayoutManager(this)
        mShopAdapter = StaffTypeAdapter(this)
        mRv.adapter = mShopAdapter
        mShopAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<StaffType> {
            override fun onItemClick(item: StaffType, position: Int) {
                var intent = Intent()
                intent.putExtra(BaseConstant.KEY_SHOP, item)
                setResult(ProvideReqCode.CODE_RESULT_SHOP, intent)
                finish()
            }
        })
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        mBasePresenter.getStaffTypeList()
    }

    override fun injectComponent() {
        DaggerManagerComponent.builder().activityComponent(mActivityComponent).managerModule(ManagerModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onGetStaffTypeSuccess(result: MutableList<StaffType>) {
        mRefreshLayout.endLoadingMore()
        mRefreshLayout.endRefreshing()
        if (result != null) {
            mShopAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }
}