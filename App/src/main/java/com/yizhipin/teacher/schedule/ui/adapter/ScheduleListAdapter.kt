package com.yizhipin.teacher.schedule.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.yizhipin.R
import com.yizhipin.base.utils.GlideUtils
import com.yizhipin.data.response.ScheduleItemBean
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Creator Qi
 * Date 2018/12/19
 */
class ScheduleListAdapter : RecyclerView.Adapter<ViewHolder>() {

    private val mData = ArrayList<ScheduleItemBean>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = View.inflate(p0.context, R.layout.item_schedule_list, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val scheduleItemBean = mData[position]
        GlideUtils.loadImage(holder.leftIcon.context, scheduleItemBean.imgurl, holder.leftIcon)
        holder.photographyType.text = scheduleItemBean.type
        holder.photographySubTitle.text = scheduleItemBean.itemType
        holder.price.text = String.format(holder.price.resources.getString(R.string.price_photography), scheduleItemBean.amount)
        var phase = 0
        if ("0" == scheduleItemBean.status) {
            phase = R.string.phasePrepare
        } else if ("1" == scheduleItemBean.status)
            phase = R.string.phaseFinished
        holder.phaseView.setText(phase)
        holder.dateView.text = scheduleItemBean.scheduleDate
    }

    fun setData(data: MutableList<ScheduleItemBean>) {
        data.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun addData(data: MutableList<ScheduleItemBean>) {
        if (data.isEmpty()) return
        val size = this.mData.size
        mData.addAll(data)
        notifyItemRangeInserted(size,data.size)
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val leftIcon = itemView.findViewById<ImageView>(R.id.leftIcon)!!//左边图标
    val rightIcon = itemView.findViewById<CircleImageView>(R.id.rightIcon)!!//左边图标
    val photographyType = itemView.findViewById<TextView>(R.id.photographyTitleView)!!//摄影标题
    val photographySubTitle = itemView.findViewById<TextView>(R.id.photographySubTitleView)!!//摄影副标题
    val price = itemView.findViewById<TextView>(R.id.priceView)!!//摄影价格
    val phaseView = itemView.findViewById<TextView>(R.id.phaseView)!!//摄影阶段
    val dateView = itemView.findViewById<TextView>(R.id.dateView)!!//摄影阶段

}