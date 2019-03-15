package com.qi.management.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qi.management.R
import com.yizhipin.base.data.response.ManagerOrder
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_staff_order_item.view.*

class ManagerOrderAdapter(val context: Context) : BaseRecyclerViewAdapter<ManagerOrder, ManagerOrderAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_staff_order_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        with(model) {

            holder.itemView.mIv.loadUrl(imgurl)
            holder.itemView.mNameTv.text = nickname
            holder.itemView.mAmountTv.text = "¥$nickname"
            holder.itemView.mContentTv.text = introduction
            holder.itemView.mDateTv.text = orderTime
            holder.itemView.mUserNameTv.text = nickname

            if (headImgurl.isNullOrBlank()) {
                holder.itemView.mHeadIv.setImageResource(R.drawable.avatar_w)
            } else {
                holder.itemView.mHeadIv.loadUrl(headImgurl!!)
            }


        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
