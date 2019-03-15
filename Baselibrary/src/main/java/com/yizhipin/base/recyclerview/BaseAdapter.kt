package com.yizhipin.base.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Creator Qi
 * Date 2019/3/2
 */
abstract class BaseAdapter<Data, ViewHolder : BaseViewHolder<Data>> : RecyclerView.Adapter<ViewHolder>() {

    private val mData: MutableList<Data> = arrayListOf()

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.setData(mData[p1])
    }

    fun addAll(data: MutableList<Data>) {
        val index = mData.size
        if (data.size > 0) {
            mData.addAll(data)
            notifyItemInserted(index)
        }
    }

    fun remove(data: Data) {
        if (mData.size > 0) {
            val index = mData.indexOf(data)
            if (index >= 0) {
                mData.removeAt(index)
                notifyItemRemoved(index)
            }
        }
    }

    fun remove(data: MutableList<Data>) {
        if (mData.size > 0) {
            mData.removeAll(data)
            notifyDataSetChanged()
        }
    }

    fun getAll(): MutableList<Data> {
        return mData
    }

    fun getItem(position: Int): Data? {
        return if (position < 0 || position >= mData.size)
            null
        else mData[position]
    }


}

abstract class BaseViewHolder<Data>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun setData(data: Data)

}