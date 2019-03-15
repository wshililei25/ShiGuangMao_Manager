package com.qi.management.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qi.management.R
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.goods.data.response.Complain
import kotlinx.android.synthetic.main.layout_complain_item.view.*

class ComplainAdapter(val context: Context) : BaseRecyclerViewAdapter<Complain, ComplainAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_complain_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        with(model) {

            holder.itemView.mNameTv.text = nickname
            holder.itemView.mContentTv.text = content
            holder.itemView.mDateTv.text = createTime

            if (imgurl.isNullOrBlank()) {
                holder.itemView.mIv.setImageResource(R.drawable.avatar_w)
            } else {
                holder.itemView.mIv.loadUrl(imgurl!!)
            }

        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
