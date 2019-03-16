package com.yizhipin.usercenter.ui.adapter


import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.base.data.response.Works
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.usercenter.R
import kotlinx.android.synthetic.main.layout_work_item.view.*

class TeacherWorkAdapter(var context: Context) : BaseRecyclerViewAdapter<Works, TeacherWorkAdapter.ViewHolder>(context) {

    private lateinit var mAdapter: TeacherWorkImageAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_work_item, null)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.mNameTv.text = model.address
        holder.itemView.mRv.layoutManager = GridLayoutManager(context, 4)
        mAdapter = TeacherWorkImageAdapter(context)
        holder.itemView.mRv.adapter = mAdapter
        var listResult = mutableListOf<String>()
        var list = model.imgurls.split(",").toMutableList()
        for (l in list) {
            listResult.add(l)
        }
        mAdapter.setData(listResult)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
