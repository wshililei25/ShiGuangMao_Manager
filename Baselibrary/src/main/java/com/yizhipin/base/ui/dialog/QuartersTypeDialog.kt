package com.yizhipin.base.ui.dialog

import android.app.ActionBar.LayoutParams
import android.app.Dialog
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.LayoutInflater
import com.eightbitlab.rxbus.Bus
import com.yizhipin.base.R
import com.yizhipin.base.common.QuartersType
import com.yizhipin.base.event.QuartersTypeCheckedEvent
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.ui.adapter.TakePhotoTypeAdapter
import com.yizhipin.usercenter.data.response.TakePhoteType
import kotlinx.android.synthetic.main.layout_dialog_type.*

/**
 * Created by ${XiLei} on 2017/2/10.
 */
class QuartersTypeDialog(context: Context) : Dialog(context, R.style.HB_Dialog) {

    private lateinit var mTypeAdapter: TakePhotoTypeAdapter

    init {
        this.window!!.setWindowAnimations(R.style.animinandout)
        initView(context)
    }

    private fun initView(context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.layout_dialog_type, null)
        setContentView(view)
        setCanceledOnTouchOutside(true)
        val window = window
        val params = window!!.attributes
        params.width = LayoutParams.MATCH_PARENT
        params.height = LayoutParams.WRAP_CONTENT
        params.gravity = Gravity.BOTTOM
        window.attributes = params

        var list = mutableListOf<TakePhoteType>()
        list.add(TakePhoteType(QuartersType.QUARTERS_HUNSHA, "婚纱摄影"))
        list.add(TakePhoteType(QuartersType.QUARTERS_XIEZHEN, "写真摄影"))
        list.add(TakePhoteType(QuartersType.QUARTERS_BAOBAO, "宝宝摄影"))
        mRv.layoutManager = LinearLayoutManager(context)
        mTypeAdapter = TakePhotoTypeAdapter(context!!)
        mRv.adapter = mTypeAdapter
        mTypeAdapter.dataList = list

        mTypeAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<TakePhoteType> {
            override fun onItemClick(item: TakePhoteType, position: Int) {
                Bus.send(QuartersTypeCheckedEvent(item))
                dismiss()
            }

        })
    }

}
