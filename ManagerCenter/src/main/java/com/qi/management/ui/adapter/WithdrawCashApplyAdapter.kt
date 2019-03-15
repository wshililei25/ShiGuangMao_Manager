package com.qi.management.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qi.management.R
import com.yizhipin.base.data.response.WithdrawCashApply
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_withdraw_cash_item.view.*

class WithdrawCashApplyAdapter(val context: Context) : BaseRecyclerViewAdapter<WithdrawCashApply, WithdrawCashApplyAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_withdraw_cash_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        with(model) {

            holder.itemView.mNameTv.text = nickname
            holder.itemView.mTypeTv.text = teacherType
            holder.itemView.mDateTv.text = "${createTime}  提现申请"
            holder.itemView.mAmountTv.text = "¥$amount "

            if (imgurl.isNullOrBlank()) {
                holder.itemView.mIv.setImageResource(R.drawable.avatar_w)
            } else {
                holder.itemView.mIv.loadUrl(imgurl)
            }

            when (status) {
                "0" -> holder.itemView.mAgreeTv.text = "同意"
                "1" -> holder.itemView.mAgreeTv.text = "已同意"
                "2" -> holder.itemView.mAgreeTv.text = "已拒绝"
            }

        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
