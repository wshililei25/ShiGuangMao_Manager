package com.yizhipin.usercenter.ui.adapter


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.usercenter.R
import kotlinx.android.synthetic.main.layout_work_imiage_item.view.*

class TeacherWorkImageAdapter(var context: Context) : BaseRecyclerViewAdapter<String, TeacherWorkImageAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_work_imiage_item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.mIv.loadUrl(model)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
