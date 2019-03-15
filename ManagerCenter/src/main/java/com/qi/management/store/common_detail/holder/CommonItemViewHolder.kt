package com.qi.management.store.common_detail.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.qi.management.R
import com.qi.management.bean.CommonDetailBean
import com.qi.management.store.common_detail.view.CommonDetailActivity
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.recyclerview.BaseViewHolder

/**
 * Creator Qi
 * Date 2019/3/3
 */
class CommonItemViewHolder(itemView: View) : BaseViewHolder<CommonDetailBean>(itemView) {

    private val imageView = itemView.findViewById<ImageView>(R.id.imageView)
    private val titleText = itemView.findViewById<TextView>(R.id.titleText)
    private val priceText = itemView.findViewById<TextView>(R.id.priceText)

    override fun setData(commonDetailBean: CommonDetailBean) {
        imageView.loadUrl(commonDetailBean.imgurl)
        titleText.text = commonDetailBean.title
        priceText.text = "￥ ${commonDetailBean.amount}"
        itemView.setOnClickListener { CommonDetailActivity.navigation(commonDetailBean, CommonDetailActivity.PageType.Costume) }
    }
}
