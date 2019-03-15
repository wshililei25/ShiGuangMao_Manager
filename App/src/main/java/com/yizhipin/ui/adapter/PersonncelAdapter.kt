package com.yizhipin.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R
import com.yizhipin.base.data.response.GridItem
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_home_grid.view.*

/**
 * Creator Qi
 * Date 2019/1/27
 */
class PersonncelAdapter(val context: Context) : BaseRecyclerViewAdapter<GridItem, PersonncelAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_home_grid, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.text.setText(model.name)
        val drawable = holder.itemView.text.resources.getDrawable(model.drawable)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        holder.itemView.text.setCompoundDrawables(null, drawable, null, null)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}