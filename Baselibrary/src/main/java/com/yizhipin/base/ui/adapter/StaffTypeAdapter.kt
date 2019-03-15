package com.yizhipin.base.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.R
import com.yizhipin.usercenter.data.response.StaffType
import kotlinx.android.synthetic.main.layout_shop_item.view.*

class StaffTypeAdapter(var context: Context) : BaseRecyclerViewAdapter<StaffType, StaffTypeAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_shop_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        with(model) {
            holder.itemView.mNameTv.text = positionName
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
