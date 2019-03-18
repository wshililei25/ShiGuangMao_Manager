package com.qi.management.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.eightbitlab.rxbus.Bus
import com.qi.management.R
import com.qi.management.injection.component.DaggerManagerComponent
import com.qi.management.injection.module.ManagerModule
import com.qi.management.presenter.ManagerOrderDetailsPresenter
import com.qi.management.presenter.view.ManagerOrderDetailsView
import com.qi.management.ui.adapter.AppointDressAdapter
import com.qi.management.ui.adapter.AppointFeatureSpotAdapter
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.AppiontDress
import com.yizhipin.base.data.response.AppiontSport
import com.yizhipin.base.data.response.BrideInfo
import com.yizhipin.base.data.response.ManagerOrder
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.provider.common.ProvideReqCode
import com.yizhipin.provider.common.ProviderConstant
import com.yizhipin.usercenter.data.response.ManagerOrderDetails
import kotlinx.android.synthetic.main.activity_manager_order_details.*

/**
 * Created by ${XiLei} on 2018/9/22.
 * 管理端订单详情
 */
class ManagerOrderDetailsActivity : BaseMvpActivity<ManagerOrderDetailsPresenter>(), ManagerOrderDetailsView, View.OnClickListener {

    private lateinit var mManagerOrder: ManagerOrder

    private lateinit var mOrderDetails: ManagerOrderDetails
    private lateinit var mAppointDressAdapter: AppointDressAdapter
    private lateinit var mAppointFeatureSpotAdapter: AppointFeatureSpotAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manager_order_details)

        initView()
        loadOrderDetails()
    }

    override fun injectComponent() {
        DaggerManagerComponent.builder().activityComponent(mActivityComponent).managerModule(ManagerModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun initView() {

        mManagerOrder = intent.getParcelableExtra(BaseConstant.KEY_ORDER)

//        mUserNameIv.loadUrl(AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON))
//        mUserNameTv.text = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NICKNAME)

        //指定服装
        var linearLayoutManager = LinearLayoutManager(this!!)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        mAppointDressRv.layoutManager = linearLayoutManager
        mAppointDressAdapter = AppointDressAdapter(this)
        mAppointDressRv.adapter = mAppointDressAdapter
        var list = ArrayList<AppiontDress>()
        var appointDress1 = AppiontDress()
        appointDress1.title = "指定服装1"
        list.add(appointDress1)
        var appointDress2 = AppiontDress()
        appointDress2.title = "指定服装2"
        list.add(appointDress2)
        mAppointDressAdapter.setData(list)

        //指定景点
        mAppointFeatureSpotRv.layoutManager = GridLayoutManager(this, 3)
        mAppointFeatureSpotAdapter = AppointFeatureSpotAdapter(this)
        mAppointFeatureSpotRv.adapter = mAppointFeatureSpotAdapter
        var list1 = ArrayList<AppiontSport>()
        var appiontSport1 = AppiontSport()
        appiontSport1.name = "指定景点1"
        list1.add(appiontSport1)
        var appiontSport2 = AppiontSport()
        appiontSport2.name = "指定景点2"
        list1.add(appiontSport2)
        mAppointFeatureSpotAdapter.setData(list1)

        mEditTv.onClick(this)
        mCameramanView.onClick(this)
        mDresserView.onClick(this)
    }

    /**
     * 获取订单详情
     */
    private fun loadOrderDetails() {
        var map = mutableMapOf<String, String>()
        map.put("orderType", mManagerOrder.orderType)
        map.put("id", mManagerOrder.id)
        mBasePresenter.getManagerOrderDetails(map)
    }

    override fun onGetManagerOrderDetailsSuccess(result: ManagerOrderDetails) {
        with(result) {
            mOrderDetails = result
            mNumTv.text = title
            mMealPriceTv.text = getString(R.string.rmb).plus(amount)
//            mShopNameTv.text = packages[0].shopName
            mOrderNumberTv.text = ordernum
            mCreateTimeTv.text = createTime
            mSubscriptionTimeTv.text = earnestPaytime
            mSubscriptionAmountTv.text = if (earnestPay) "¥ ${earnestMoney}" else "¥ 0.00"
            mBalancePaymentTv.text = if (null == payTime) "待付款" else payTime
            mGoodsIv.loadUrl(imgurl)
        }

    }

    override fun onClick(v: View) {
        when (v.id) {

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            ProvideReqCode.CODE_RESULT_BRIDE_INFO -> { //添加用户信息返回
                val mBrideInfo = data!!.getParcelableExtra<BrideInfo>(BaseConstant.KEY_BRIDE_INFO)
                with(mBrideInfo) {
                    mBridegroomTv.text = bridegroom
                    mBrideTv.text = bride
                    mWeddingDayTv.text = weddingDate
                    mTimeTv.text = photoTime
                    mReceiveTv.text = recevice
                }
            }
            ProvideReqCode.CODE_RESULT_ADD_CAMERAMAN -> { //添加摄影师返回
                when (requestCode) {
                    1001 -> {
                        mCameramanTv.text = data?.getStringExtra(BaseConstant.KEY_ADD_CAMERAMAN)
                        mCameramanAmountTv.text = data?.getStringExtra(BaseConstant.KEY_ADD_CAMERAMAN_AMOUNT)
                        mCameramanIv.loadUrl(data?.getStringExtra(BaseConstant.KEY_ADD_CAMERAMAN_URL)!!)
                    }
                    1002 -> {
                        mDresserTv.text = data?.getStringExtra(BaseConstant.KEY_ADD_CAMERAMAN)
                        mDresserAmountTv.text = data?.getStringExtra(BaseConstant.KEY_ADD_CAMERAMAN_AMOUNT)
                        mDresserIv.loadUrl(data?.getStringExtra(BaseConstant.KEY_ADD_CAMERAMAN_URL)!!)
                    }
                }
            }
            ProvideReqCode.CODE_RESULT_DRESS_WOMEN -> { //添加女装返回
                mAppointDressAdapter.dataList.get(data!!.getIntExtra(BaseConstant.KEY_DRESS_POSITION, 0)).womenImage = data!!.getStringExtra(BaseConstant.KEY_DRESS_WOMEN_IMAGE)
                mAppointDressAdapter.notifyDataSetChanged()
            }
            ProvideReqCode.CODE_RESULT_DRESS_MAN -> { //添加男装返回
                mAppointDressAdapter.dataList.get(data!!.getIntExtra(BaseConstant.KEY_DRESS_POSITION, 0)).manImage = data!!.getStringExtra(BaseConstant.KEY_DRESS_MAN_IMAGE)
                mAppointDressAdapter.notifyDataSetChanged()
            }
            ProvideReqCode.CODE_RESULT_FEATURE -> { //添加景点返回
                mAppointFeatureSpotAdapter.dataList.get(data!!.getIntExtra(BaseConstant.KEY_DRESS_POSITION, 0)).name = data!!.getStringExtra(BaseConstant.KEY_SCENIC_NAME)
                mAppointFeatureSpotAdapter.dataList.get(data!!.getIntExtra(BaseConstant.KEY_DRESS_POSITION, 0)).amount = data!!.getStringExtra(BaseConstant.KEY_SCENIC_AMOUNT)
                mAppointFeatureSpotAdapter.dataList.get(data!!.getIntExtra(BaseConstant.KEY_DRESS_POSITION, 0)).image = data!!.getStringExtra(BaseConstant.KEY_SCENIC_IMAGE)
                mAppointFeatureSpotAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

}