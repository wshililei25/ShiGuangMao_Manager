package com.qi.management.store.common_detail.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.yizhipin.base.ext.loadUrl

/**
 * 轮播图Adapter
 * Creator Qi
 * Date 2019/2/24
 */
class CombosDetailBannerAdapter(private val urls: MutableList<String>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.layoutParams = ViewGroup.LayoutParams(-1, -1)
        imageView.loadUrl(urls[position])
        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (`object` is View)
            container.removeView(`object`)
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        return urls.size

    }
}