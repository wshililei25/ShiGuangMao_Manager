package com.qi.management.ui.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.qi.management.R
import com.qi.management.injection.component.DaggerManagerComponent
import com.qi.management.injection.module.ManagerModule
import com.qi.management.presenter.StoreInfoPresenter
import com.qi.management.presenter.view.StoreInfoView
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.Store
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.utils.CityUtil
import com.yizhipin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_store_info_management.*

/**
 * 门店信息管理
 * Creator Qi
 * Date 2019/1/28
 */
@Route(path = RouterPath.Management.STORE_INFORMATION_MANAGEMENT)
class StoreInfoManagementActivity : BaseMvpActivity<StoreInfoPresenter>(), StoreInfoView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(): Int {
        return R.layout.activity_store_info_management
    }

    override fun injectComponent() {
        DaggerManagerComponent.builder().activityComponent(mActivityComponent).managerModule(ManagerModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        cityLayout.setOnClickListener {
            hideSoftInput()
            CityUtil.getInstance(this).showPickerView(this) { province, city, district -> storeCityText.text = "$province  $city  $district" }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        var map = mutableMapOf<String, String>()
        map.put("id", AppPrefsUtils.getString(BaseConstant.KEY_SHOP_ID))
        mBasePresenter.getStoreInfo(map)
    }

    override fun onGetStoreInfoResult(store: Store) {
        storeIcon.loadUrl(store.imgurl)
        storeNameText.setText(store.storeName)
        storeCityText.text = store.city
        storeAddressText.setText(store.detail)
        storeProfileText.text = store.content
    }

}