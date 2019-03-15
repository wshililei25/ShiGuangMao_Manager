package com.qi.management.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qi.management.R
import com.yizhipin.base.data.response.News
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_news_item.view.*

class NewsAdapter(val context: Context) : BaseRecyclerViewAdapter<News, NewsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_news_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        with(model) {

            holder.itemView.mNameTv.text = nickname
            holder.itemView.mContentTv.text = content
            holder.itemView.mDateTv.text = pushTime
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
