package com.qi.management.store.store_info_management

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.qi.management.R
import com.qi.management.bean.StoreBean
import com.qi.management.store.store_info_management.dagger.DaggerComponent
import com.qi.management.store.store_info_management.dagger.Module
import com.qi.management.store.store_info_management.mvp.StoreInfoManagementPresenterImpl
import com.qi.management.store.store_info_management.mvp.StoresContract
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.utils.CityUtil
import com.yizhipin.base.utils.PermissionRequestCode.CAMERA
import com.yizhipin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_store_info_management.*

/**
 * 门店信息管理
 * Creator Qi
 * Date 2019/1/28
 */
@Route(path = RouterPath.Management.STORE_INFORMATION_MANAGEMENT)
class StoreInfoManagementActivity : BaseMvpActivity<StoreInfoManagementPresenterImpl>(), StoresContract.IView {

    //    private var iconPop: IconPop? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        val permissions = HashMap<String, String>()
        permissions[android.Manifest.permission.CAMERA] = resources.getString(R.string.hintTakePhoto)
        permissions[android.Manifest.permission.WRITE_EXTERNAL_STORAGE] = resources.getString(R.string.hintTakePhotoWrite)
        checkPermission(permissions, CAMERA)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(): Int {
        return R.layout.activity_store_info_management
    }

    override fun injectComponent() {
        DaggerComponent.builder().activityComponent(mActivityComponent).module(Module(this)).build().inject(this)
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
//        iconPop = IconPop(this, TakePhotoListenerImpl(this))
//        titleView.setOnLeftIconClickListener { onBackPressed() }
//        saveText.setOnClickListener { mBasePresenter.saveStoreInfo() }
        storeIconLayout.setOnClickListener { showIconPop() }
        cityLayout.setOnClickListener {
            hideSoftInput()
            CityUtil.getInstance(this).showPickerView(this) { province, city, district -> storeCityText.text = "$province  $city  $district" }
        }
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        mBasePresenter.getStoreInfo()
    }

    override fun showData(store: StoreBean) {
        showIcon(store.imgurl)
        storeNameText.setText(store.storeName)
        storeCityText.text = store.city
        storeAddressText.setText(store.detail)
        storeProfileText.text = store.content
    }

    override fun showIcon(imageUrl: String?) {
        mBasePresenter.setImageUrl(imageUrl)
        runOnUiThread {
            Glide.with(storeIcon.context)
                    .load(AppPrefsUtils.getString(BaseConstant.IMAGE_ADDRESS) + imageUrl)
                    .into(storeIcon)
        }

    }

    /**
     * 更换头像
     */
    private fun showIconPop() {
        hideLoading()
//        iconPop?.show()
    }

    override fun getStoreName(): String {
        return storeNameText.text.toString()
    }

    override fun getDetailAddress(): String {
        return storeAddressText.text.toString()
    }

    override fun getStoreContent(): String {
        return storeProfileText.text.toString()
    }

    override fun getCity(): String {
        return storeCityText.text.toString()
    }

    override fun getContext(): Context {
        return this
    }


    override fun onDestroy() {
        /*if (iconPop != null) {
            iconPop!!.destroy()
            iconPop = null
        }*/
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
       /* if (iconPop != null) {
            iconPop!!.onActivityResult(requestCode, resultCode, data)
        }*/
    }
}