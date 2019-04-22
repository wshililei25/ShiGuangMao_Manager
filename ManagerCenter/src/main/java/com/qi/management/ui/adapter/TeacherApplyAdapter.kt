package com.qi.management.ui.adapter

import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.qi.management.R
import com.yizhipin.base.common.QuartersType
import com.yizhipin.base.common.TeacherType
import com.yizhipin.base.data.response.Teacher
import com.yizhipin.base.event.TeacherApplyEvent
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.ext.onClick
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_teacher_apply_item.view.*

class TeacherApplyAdapter(val context: Context) : BaseRecyclerViewAdapter<Teacher, TeacherApplyAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_teacher_apply_item, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        with(model) {

            holder.itemView.mNameTv.text = webUser.nickname
            holder.itemView.mDateTv.text = webUser.registerTime

            if (webUser.imgurl.isNullOrBlank()) {
                holder.itemView.mIv.setImageResource(R.drawable.avatar_w)
            } else {
                holder.itemView.mIv.loadUrl(webUser.imgurl)
            }

            when (teacherType) {
                TeacherType.TEACHER_SHEYING -> holder.itemView.mTypeTv.text = context.getString(R.string.cameraman)
                TeacherType.TEACHER_HUAZHUANG -> holder.itemView.mTypeTv.text = context.getString(R.string.dresser)
            }
            when (applyType) {
                QuartersType.QUARTERS_HUNSHA -> holder.itemView.mType1Tv.text = context.getString(R.string.veil_photography)
                QuartersType.QUARTERS_XIEZHEN -> holder.itemView.mType1Tv.text = context.getString(R.string.describe_photography)
                QuartersType.QUARTERS_BAOBAO -> holder.itemView.mType1Tv.text = context.getString(R.string.baby_photography)
            }

            when (status) {
                "0" -> {
                    holder.itemView.mAgreeTv.text = "同意"
                    holder.itemView.mAgreeTv.setBackgroundResource(R.drawable.shape_red_solid_corners)

                    holder.itemView.mAgreeTv.onClick {
                        Bus.send(TeacherApplyEvent(uid))
                    }
                }
                "1" -> {
                    holder.itemView.mAgreeTv.text = "已同意"
                    holder.itemView.mAgreeTv.setBackgroundResource(R.drawable.shape_grey_solid_corners)
                }
                "2" -> {
                    holder.itemView.mAgreeTv.text = "已拒绝"
                    holder.itemView.mAgreeTv.setBackgroundResource(R.drawable.shape_grey_solid_corners)
                }
            }

        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
