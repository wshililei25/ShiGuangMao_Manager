package com.qi.management.ui.adapter

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qi.management.R
import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_teacher_item.view.*

class TeacherAdapter(val context: Context) : BaseRecyclerViewAdapter<Teacher, TeacherAdapter.ViewHolder>(context) {

    private var mMap = mapOf<Int, Boolean>()
    private lateinit var mAdapter: TeacherImageAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_teacher_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        with(model) {

            holder.itemView.mIv.loadUrl(webUser.imgurl)
            holder.itemView.mNumTv.text = webUser.credit.toString()
            holder.itemView.mNameTv.text = webUser.nickname.plus(" | ").plus(teacherType)
            holder.itemView.mContentTv.text = selfIntroduction
            holder.itemView.mPriceTv.text = "¥${webUser.photoAmount}/套服装"

            var listResult = mutableListOf<String>()
            if(null != works && works.size>0){
                if (works[0].imgurls.contains(",")) {
                    var list = works[0].imgurls!!.split(",").toMutableList()
                    for (l in list) {
                        listResult.add(l)
                    }
                } else {
                    listResult.add(works[0].imgurls)
                }
            }
            holder.itemView.mRv.layoutManager = GridLayoutManager(context!!, 3)
            mAdapter = TeacherImageAdapter(context!!)
            mAdapter.setData(listResult)
            holder.itemView.mRv.adapter = mAdapter
        }

        var mutableMap = mMap.toMutableMap()
        for ((index) in dataList.withIndex()) {
            mutableMap.put(index, false)
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
