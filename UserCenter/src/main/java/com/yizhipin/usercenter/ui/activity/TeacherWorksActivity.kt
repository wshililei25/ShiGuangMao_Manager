package com.yizhipin.usercenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kennyc.view.MultiStateView
import com.yizhipin.base.data.response.Store
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.ui.adapter.SelectTextAdapter
import com.yizhipin.usercenter.R
import com.yizhipin.usercenter.injection.component.DaggerUserComponent
import com.yizhipin.usercenter.injection.module.UserModule
import com.yizhipin.usercenter.presenter.ShopPresenter
import com.yizhipin.usercenter.presenter.view.ShopView
import kotlinx.android.synthetic.main.activity_teacher_works.*
import org.jetbrains.anko.startActivity

/**
 * Created by ${XiLei} on 2018/9/24.
 * 添加作品
 */

class TeacherWorksActivity : BaseMvpActivity<ShopPresenter>(), ShopView, View.OnClickListener {

    private lateinit var mShopAdapter: SelectTextAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_works)

        initView()
    }

    private fun initView() {

        mRv.layoutManager = LinearLayoutManager(this)
        mShopAdapter = SelectTextAdapter(this)
        mRv.adapter = mShopAdapter

        mHeaderBar.getRightTv().onClick {
            startActivity<TeacherApplySuccessActivity>()
        }
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

    override fun onClick(v: View?) {

    }

}