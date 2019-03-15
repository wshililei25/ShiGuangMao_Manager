package com.qi.management.store.production_management

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.qi.management.R
import com.qi.management.bean.CommonDetailBean
import com.qi.management.store.common_detail.view.CommonDetailActivity
import com.yizhipin.base.ext.loadUrl

/**
 * 产品列表
 * Creator Qi
 * Date 2019/2/26
 */
class ProductionListAdapter : RecyclerView.Adapter<ProductionViewHolder>() {

    private val mData: MutableList<CommonDetailBean> = arrayListOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ProductionViewHolder {
        val view = View.inflate(p0.context, R.layout.item_production_list, null)
        return ProductionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(p0: ProductionViewHolder, p1: Int) {
        p0.setData(mData[p1])
    }

    fun addAll(data: MutableList<CommonDetailBean>) {
        if (data.size > 0) {
            val index = mData.size
            mData.addAll(data)
            notifyItemInserted(index)
        }
    }
}

class ProductionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val imageView = itemView.findViewById<ImageView>(R.id.imageView)
    private val titleText = itemView.findViewById<TextView>(R.id.titleText)
    private val priceText = itemView.findViewById<TextView>(R.id.priceText)

    fun setData(commonDetailBean: CommonDetailBean) {
        imageView.loadUrl(commonDetailBean.imgurl)
        titleText.text = commonDetailBean.title
        priceText.text = "￥ ${commonDetailBean.price}"
        itemView.setOnClickListener { CommonDetailActivity.navigation(commonDetailBean, CommonDetailActivity.PageType.Production) }
    }
}