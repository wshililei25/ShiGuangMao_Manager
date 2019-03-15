package com.qi.management.store.common_detail.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.qi.management.R
import com.qi.management.bean.CommonDetailBean
import com.qi.management.store.common_detail.holder.CommonItemViewHolder
import com.yizhipin.base.recyclerview.BaseAdapter

/**
 * 水平列表itemAdapter
 * Creator Qi
 * Date 2019/3/3
 */
class CommonItemAdapter : BaseAdapter<CommonDetailBean, CommonItemViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CommonItemViewHolder {
        val itemView = View.inflate(p0.context, R.layout.item_production_list, null)
        val size = (p0.context.resources.displayMetrics.density * 80).toInt()
        itemView.findViewById<ImageView>(R.id.imageView).layoutParams = RelativeLayout.LayoutParams(size, size)
        return CommonItemViewHolder(itemView)
    }
}