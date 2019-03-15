package com.qi.management.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codbking.widget.DatePickDialog
import com.codbking.widget.OnSureLisener
import com.codbking.widget.bean.DateType
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.qi.management.R
import com.qi.management.injection.component.DaggerManagerComponent
import com.qi.management.injection.module.ManagerModule
import com.qi.management.presenter.FinancialStatisticsPresenter
import com.qi.management.presenter.view.FinancialStatisticsView
import com.yizhipin.base.common.BaseConstant
import com.yizhipin.base.data.response.Financial
import com.yizhipin.base.event.TakePhotoTypeCheckedEvent
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.dialog.TakePhotoTypeDialog
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.base.utils.AppPrefsUtils
import com.yizhipin.base.utils.DateUtils
import kotlinx.android.synthetic.main.fragment_financial_statistics.*
import java.util.*

/**
 * Created by ${XiLei} on 2018/9/25.
 */
class FinancialStatisticsFragment : BaseMvpFragment<FinancialStatisticsPresenter>(), FinancialStatisticsView, View.OnClickListener {

    var mType = "all"

    private lateinit var mDatePickDialog: DatePickDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_financial_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initDialog()
        initObserve()
        loadData()
    }

    private fun initView() {
        mShopNameTv.text = AppPrefsUtils.getString(BaseConstant.KEY_SHOP_NAME)
        mDateTv.text = DateUtils.getNow(DateUtils.FORMAT_SHORT)

        mTypeTv.onClick(this)
        mDateTv.onClick(this)
    }

    private fun initDialog() {
        mDatePickDialog = DatePickDialog(activity)
        //设置上下年分限制
        mDatePickDialog.setYearLimt(5)
        //设置标题
//            mDialog.setTitle("选择时间")
        //设置类型
        mDatePickDialog.setType(DateType.TYPE_YMD)
        //设置消息体的显示格式，日期格式
        mDatePickDialog.setMessageFormat(DateUtils.FORMAT_SHORT_CN)
        //设置选择回调
        mDatePickDialog.setOnChangeLisener(null)
    }

    private fun loadData() {
        var map = mutableMapOf<String, String>()
        map.put("storeId", AppPrefsUtils.getString(BaseConstant.KEY_SHOP_ID))
        map.put("queryTimeType", arguments!!.getString(BaseConstant.KEY_ORDER_STATUS, "-1").toString())
        map.put("queryTime", mDateTv.text.toString())
        map.put("queryType", mType)
        mBasePresenter.getFinancial(map)
    }

    override fun injectComponent() {
        DaggerManagerComponent.builder().activityComponent(mActivityComponent).managerModule(ManagerModule()).build().inject(this)
        mBasePresenter.mView = this
    }

    override fun onGetFinancialResult(result: Financial) {
        with(result) {
            mTv1.text = "用户新增   $userCount"
            mTv2.text = "订单新增   $orderCount"
            mTv3.text = "今日实收   $income"
            mTv4.text = "定金收取   $earnest"
            mTv5.text = "提现申请   $withdrawCount"
            mTv6.text = "提现金额   $withdrawAmount"
            mTv7.text = "押金总额   $depositAmout"
        }

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.mTypeTv -> {
                var customDialog = TakePhotoTypeDialog(activity!!)
                customDialog.show()
            }
            R.id.mDateTv -> {
                //设置点击确定按钮回调
                mDatePickDialog.setOnSureLisener(object : OnSureLisener {
                    override fun onSure(d: Date) {
                        mDateTv.text = DateUtils.format(d, DateUtils.FORMAT_SHORT)
                        loadData()
                    }

                })
                mDatePickDialog.show()
            }
        }
    }

    private fun initObserve() {
        Bus.observe<TakePhotoTypeCheckedEvent>()
                .subscribe { t: TakePhotoTypeCheckedEvent ->
                    run {
                        mType = t.takePhoteType.type
                        mTypeTv.text = t.takePhoteType.name
                        loadData()
                    }
                }.registerInBus(this)
    }
}