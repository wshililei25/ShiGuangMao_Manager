package com.yizhipin.paycenter.ui.activity

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alipay.sdk.app.PayTask
import com.google.gson.Gson
import com.tencent.mm.opensdk.modelpay.PayReq
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.common.WechatAppID
import com.yizhipin.base.data.response.AliPayResult
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.payresult.PayResult
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.base.ui.activity.PaySuccessActivity
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.widgets.PayRadioGroup
import com.yizhipin.base.widgets.PayRadioPurified
import com.yizhipin.paycenter.R
import com.yizhipin.provider.router.RouterPath
import com.yizhipin.shop.injection.component.DaggerPayComponent
import com.yizhipin.shop.injection.module.PayModule
import com.yizhipin.shop.presenter.PayPresenter
import com.yizhipin.shop.presenter.view.PayView
import kotlinx.android.synthetic.main.activity_recharge.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONObject

/**
 * Created by ${XiLei} on 2018/9/24.
 *
 * 押金支付
 */
@Route(path = RouterPath.PayCenter.PATH_PAY_RECHARGE)
class RechargeActivity : BaseMvpActivity<PayPresenter>(), PayView, View.OnClickListener {

    private var mPayType = "Alipay" //支付方式
    private val SDK_PAY_FLAG = 1
    private lateinit var mIWXAPI: IWXAPI

    @Autowired(name = BaseConstant.KEY_IS_FIRST) //注解接收上个页面的传参
    @JvmField
    var mIsFirst: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recharge)

        initView()
        initWechat()
    }

    override fun injectComponent() {
        DaggerPayComponent.builder().activityComponent(mActivityComponent).payModule(PayModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    private fun initView() {

        mBtn.onClick(this)
        mPayRadioGroup.setOnCheckedChangeListener(object : PayRadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(group: PayRadioGroup, checkedId: Int) {
                for (i in 0 until group.getChildCount()) {
                    (group.getChildAt(i) as PayRadioPurified).setChangeImg(checkedId)
                }
                if (mAliRadio.isChecked) {
                    mPayType = "Alipay"
                }
                if (mWechatRadio.isChecked) {
                    mPayType = "Weixin"
                }
            }
        })
    }

    /**
     * WeChat Pay注册
     */
    private fun initWechat() {
        mIWXAPI = WXAPIFactory.createWXAPI(this, null)
        when (AppPrefsUtils.getString(BaseConstant.KEY_SHOP_NAME)) {
            "临汾店" -> mIWXAPI.registerApp(WechatAppID.LINFEN)
            "三亚店" -> mIWXAPI.registerApp(WechatAppID.SANYA)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.mBtn -> {

                var map = mutableMapOf<String, String>()
                map.put("uid", AppPrefsUtils.getString(BaseConstant.KEY_SP_USER_ID))
                map.put("amount", "1000") //暂时注释
//                map.put("amount", "0.01")
                map.put("payType", mPayType)
                mBasePresenter.rechargeCashPledge(map)
            }
        }
    }

    override fun onRechargeSuccess(result: String) {
        when (mPayType) {

            "Alipay" -> {
                val payRunnable = Runnable {
                    val alipay = PayTask(this@RechargeActivity)
                    val result = alipay.payV2(result, true)
                    Log.i("XiLei", "aliPay=" + result.toString())
                    val msg = Message()
                    msg.what = SDK_PAY_FLAG
                    msg.obj = result
                    mHandler.sendMessage(msg)
                }
                // 必须异步调用
                val payThread = Thread(payRunnable)
                payThread.start()
            }
            "Weixin" -> {
                val json = JSONObject(result)
                val req = PayReq()
                req.appId = json.getString("appid")
                req.partnerId = json.getString("partnerid")
                req.prepayId = json.getString("prepayid")
                req.packageValue = json.getString("package")
                req.nonceStr = json.getString("noncestr")
                req.timeStamp = json.getString("timestamp")
                req.sign = json.getString("sign")
                toast("正常调起微信支付")
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                mIWXAPI.sendReq(req)
            }
        }
    }

    //支付宝支付 begin
    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                SDK_PAY_FLAG -> {
                    val payResult = PayResult(msg.obj as Map<String, String>)
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    val resultInfo = payResult.result// 同步返回需要验证的信息
                    val resultStatus = payResult.resultStatus
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Log.d("XiLei", "payResult=" + payResult);
//                        showAlert(this@RechargeActivity, getString(R.string.pay_success) + payResult)
                        var gson = Gson().fromJson<AliPayResult>(resultInfo, AliPayResult::class.java)
                        AppPrefsUtils.putString(BaseConstant.FOREGIFT, gson.alipay_trade_app_pay_response.total_amount) //本地保存支付的押金用作判断
                        Log.d("XiLei", "yayay0000=" + gson.alipay_trade_app_pay_response.total_amount)
                        Log.d("XiLei", "yayay11111=" + AppPrefsUtils.getString(BaseConstant.FOREGIFT))
                        startActivity<PaySuccessActivity>(BaseConstant.KEY_PAY_CONTENT to "成功支付押金" + gson.alipay_trade_app_pay_response.total_amount + "元")
                        finish()
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知
                        if (TextUtils.equals(resultStatus, "6001")) {
                            showAlert(this@RechargeActivity, getString(R.string.pay_cancel))
                            return
                        }
                        showAlert(this@RechargeActivity, getString(R.string.pay_failed))
                    }
                }
            }
        }
    }

    private fun showAlert(ctx: Context, info: String) {
        showAlert(ctx, info, null)
    }

    private fun showAlert(ctx: Context, info: String, onDismiss: DialogInterface.OnDismissListener?) {
        AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton(R.string.confirm, null)
                .setOnDismissListener(onDismiss)
                .show()
    }
    //支付宝支付 end
}