package com.qi.management.store.store_info_management.mvp

import android.text.TextUtils
import com.qi.management.R
import com.qi.management.bean.StoreBean
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.execute
import com.yizhipin.base.mvp.presenter.BasePresenter
import com.yizhipin.base.rx.CodeHandlerSubscriber
import com.yizhipin.base.utils.ToastUtils
import com.yizhipin.usercenter.utils.UserPrefsUtils
import javax.inject.Inject

/**
 * Creator Qi
 * Date 2018/12/30
 * <br/>
 * 门店信息管理Presenter
 */
class StoreInfoManagementPresenterImpl @Inject constructor(val model: StoresModelImpl, view: StoresContract.IView) : BasePresenter<StoresContract.IView>(view), StoresContract.IPresenter {

    object Style {
        /**
         * 门店
         */
        const val Store = 0
        /**
         * 人员
         */
        const val Person = 1
        /**
         * 财政
         */
        const val Finance = 2
    }

    /**
     * 请求获取门店信息
     */
    fun getStoreInfo() {
        val userInfo: UserInfo? = UserPrefsUtils.getUserInfo() ?: return
        model.getStore(userInfo!!.shopId).execute(object : CodeHandlerSubscriber<StoreBean>(mView) {
            override fun onSucceed(data: StoreBean) {
                model.storeBean = data
                mView.showData(data)
            }
        }, mLifecycleProvider)
    }

    /**
     * 设置URL
     */
    override fun setImageUrl(imageUrl: String?) {
        model.storeBean.imgurl = imageUrl ?: ""
    }

    /**
     * 保存门店信息
     */
    fun saveStoreInfo() {
        model.storeBean.storeName = mView.getStoreName()
        model.storeBean.city = mView.getCity()
        model.storeBean.detail = mView.getDetailAddress()
        model.storeBean.content = mView.getStoreContent()
        model.storeBean.uid = if (UserPrefsUtils.getUserInfo() == null) "" else UserPrefsUtils.getUserInfo()!!.id
        val legal = legal()
        if (legal != 0) {
            ToastUtils.INSTANCE.showToast(mView.getContext(), legal)
            return
        }
        model.storeBean.province = model.storeBean.city.split(" ")[0]
        model.saveStoreInfo(model.storeBean).execute(object : CodeHandlerSubscriber<StoreBean>(mView) {
            override fun onSucceed(data: StoreBean) {
                model.storeBean = data
                ToastUtils.INSTANCE.showToast(mView.getContext(), R.string.saveSuccess)
            }
        }, mLifecycleProvider)
    }

    private fun legal(): Int {
        return when {
            TextUtils.isEmpty(model.storeBean.imgurl) -> R.string.errorUploadIcon
            TextUtils.isEmpty(model.storeBean.storeName) -> R.string.errorStoreName
            TextUtils.isEmpty(model.storeBean.city) -> R.string.errorStoreCity
            TextUtils.isEmpty(model.storeBean.detail) -> R.string.errorStoreDetail
//            TextUtils.isEmpty(model.storeBean.content)->R.string.errorStoreDescription
            else -> 0
        }
    }
}