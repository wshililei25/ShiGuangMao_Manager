package com.qi.management.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qi.management.R
import com.yizhipin.base.data.response.UserInfo
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_staff_item.view.*

class StaffAdapter(val context: Context) : BaseRecyclerViewAdapter<UserInfo, StaffAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_staff_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        with(model) {
            if (imgurl.isNullOrBlank()) {
                holder.itemView.mIv.setImageResource(R.drawable.avatar_w)
            } else {
                holder.itemView.mIv.loadUrl(imgurl)
            }

            holder.itemView.mNameTv.text = nickname
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
