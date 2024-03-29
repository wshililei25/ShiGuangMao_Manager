package com.yizhipin.ordercender.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.Goods
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.utils.BaseAlertDialog
import com.yizhipin.base.utils.DateUtils
import com.yizhipin.base.widgets.PayPasswordDialog
import com.yizhipin.base.widgets.PayRadioGroup
import com.yizhipin.base.widgets.PayRadioPurified
import com.yizhipin.ordercender.R
import com.yizhipin.ordercender.common.OrderConstant
import com.yizhipin.ordercender.data.response.Coupon
import com.yizhipin.ordercender.injection.component.DaggerOrderComponent
import com.yizhipin.ordercender.injection.module.OrderModule
import com.yizhipin.ordercender.presenter.PayConfirmPresenter
import com.yizhipin.ordercender.presenter.PayConfirmView
import com.yizhipin.provider.common.ProvideReqCode
import com.yizhipin.provider.common.ProviderConstant
import com.yizhipin.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_pay_confirm.*

/**
 * Created by ${XiLei} on 2018/9/24.
 * 支付确认
 */

class PayConfirmActivity : BaseMvpActivity<PayConfirmPresenter>(), PayConfirmView, View.OnClickListener {

    @Autowired(name = BaseConstant.KEY_IS_PIN) //注解接收上个页面的传参
    @JvmField
    var mIsPin: Boolean = false
    @Autowired(name = OrderConstant.KEY_ADDRESS_ID) //注解接收上个页面的传参
    @JvmField
    var mAddressId: Int = 0

    private var mGoodsList: MutableList<Goods>? = null
    private var mGoodsId = "" //商品id
    private var mProductCounts = "" //商品数量
    private var mConponId = "" //优惠券id
    private var mType = "balance" //支付方式
    private lateinit var mPayPasswordDialog: PayPasswordDialog
    private var mAmount = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_confirm)

        initView()
    }

    override fun injectComponent() {
        DaggerOrderComponent.builder().activityComponent(mActivityComponent).orderModule(OrderModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun initView() {
        mGoodsList = intent.getParcelableArrayListExtra<Goods>(OrderConstant.KEY_GOODS_LIST) as MutableList<Goods>


        if (mIsPin) {
            for (good in mGoodsList as MutableList<Goods>) {
                mAmount += good.pinPrice!! * good.goodsCount
            }
        } else {
            for (good in mGoodsList as MutableList<Goods>) {
                mAmount += good.price!! * good.goodsCount
            }
        }
        for (list in mGoodsList!!) {
            if (list.productId.isNullOrEmpty()) { //单价买
                mGoodsId += list.id.toString().plus(",")
            } else { //购物车
                mGoodsId += list.productId.toString().plus(",")
            }
            mProductCounts += list.goodsCount.toString().plus(",")
        }
        mPostageTv.text = getString(R.string.rmb).plus(mAmount.toString())
        mPaymentTv.text = getString(R.string.rmb).plus(mAmount.toString())

     /*   if (AppPrefsUtils.getString(ProviderConstant.KEY_AMOUNT).toDouble() < mAmount) {
            mBalanceRadio.setTextDesc(getString(R.string.balance_insufficient))
        }*/

        mPayRadioGroup.setOnCheckedChangeListener(object : PayRadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: PayRadioGroup, checkedId: Int) {
                for (i in 0 until group.getChildCount()) {
                    (group.getChildAt(i) as PayRadioPurified).setChangeImg(checkedId)
                }
                if (mBalanceRadio.isChecked) {
                    mType = "balance"
                }
                if (mAliRadio.isChecked) {
                    mType = "Alipay"
                }
                if (mWechatRadio.isChecked) {
                    mType = "Weixin"
                }
            }

        })

        mPayBtn.onClick(this)
        mCouponView.onClick(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mPayBtn -> {

                if (AppPrefsUtils.getString(ProviderConstant.KEY_PAY_PWD).isNullOrEmpty()) {

                    val baseAlertDialog = BaseAlertDialog(this)
                    baseAlertDialog.setMessage("请先设置支付密码")
                    baseAlertDialog.show()
                    baseAlertDialog.setOkClickInterface(object : BaseAlertDialog.OkClickInterface {
                        override fun okClickListener() {
                            ARouter.getInstance().build(RouterPath.UserCenter.SET_PAY_PWD).navigation()
                        }
                    })
                    return
                }

                mPayPasswordDialog = PayPasswordDialog(this, R.style.PayDialog)
                mPayPasswordDialog.setDialogClick(object : PayPasswordDialog.DialogClick {
                    override fun doConfirm(password: String?) {
                        var map = mutableMapOf<String, String>()
                        map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_USER_ID))
                        map.put("conponId", mConponId)
                        map.put("payType", mType)
                        map.put("payPwd", password!!)

                        if (mGoodsList!!.get(0).primaryCategory == "homestay") { //一品小住
                            map.put("pid", mGoodsList!!.get(0).id.toString())
                            map.put("productCount", mGoodsList!!.get(0).goodsCount.toString())
                            map.put("beginTime", DateUtils.parseDateNew(mGoodsList!!.get(0).startDate!!, DateUtils.FORMAT_SHORT_CN, DateUtils.FORMAT_SHORT)!!)
                            map.put("endTime", DateUtils.parseDateNew(mGoodsList!!.get(0).endDate!!, DateUtils.FORMAT_SHORT_CN, DateUtils.FORMAT_SHORT)!!)
                            mBasePresenter.submitOrderReside(map)
                        } else {
                            map.put("pids", mGoodsId)
                            map.put("addressId", mAddressId.toString())
                            map.put("productCounts", mProductCounts)
                            mBasePresenter.submitOrder(map)
                        }

                        mPayPasswordDialog.dismiss()
                    }
                })
                mPayPasswordDialog.show()
                mPayPasswordDialog.forgetPwdTv.onClick {
                    ARouter.getInstance().build(RouterPath.UserCenter.RESET_PAY_PWD).navigation()
                }
            }

            R.id.mCouponView -> ARouter.getInstance().build(RouterPath.OrderCenter.PATH_ORDER_COUPON)
                    .withBoolean(OrderConstant.KEY_IS_PAY, true)
                    .navigation(this, ProvideReqCode.CODE_REQ_COUPON)
        }
    }

    /**
     * 下单成功
     */
    override fun onSubmitOrderSuccess(result: String) {
        setResult(ProvideReqCode.CODE_RESULT_PAY)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            ProvideReqCode.CODE_RESULT_COUPON -> {
                var coupon = data!!.getParcelableExtra<Coupon>(OrderConstant.KEY_COUPON_ITEM)

                if (mAmount >= coupon.minAmount.toDouble()) {
                    mAmountTv.text = "已自动抵扣${coupon.amount}元"
                    mConponId = coupon.id.toString()
                } else {
                    mAmountTv.text = "不可用"
                    mConponId = ""
                }
            }
        }
    }
}