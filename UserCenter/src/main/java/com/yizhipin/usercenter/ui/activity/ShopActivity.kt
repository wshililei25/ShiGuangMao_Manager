package com.yizhipin.usercenter.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.kennyc.view.MultiStateView
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.Store
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.ui.adapter.SelectTextAdapter
import com.yizhipin.provider.common.ProvideReqCode
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.injection.component.DaggerUserComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.presenter.ShopPresenter
import com.yizhipin.usercenter.presenter.view.ShopView
import kotlinx.android.synthetic.main.activity_shop.*

/**
 * Created by ${XiLei} on 2018/9/24.
 * 门店
 */

class ShopActivity : BaseMvpActivity<ShopPresenter>(), ShopView {

    private lateinit var mShopAdapter: SelectTextAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        initView()
    }

    private fun initView() {

        mRv.layoutManager = LinearLayoutManager(this)
        mShopAdapter = SelectTextAdapter(this)
        mRv.adapter = mShopAdapter
        mShopAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Store> {
            override fun onItemClick(item: Store, position: Int) {
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
        mBasePresenter.getShopList()
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent).userModule(UserModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onGetShopListSuccess(result: MutableList<Store>) {
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