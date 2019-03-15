package com.yizhipin.base.widgets

import android.content.Context
import android.widget.ImageView
import com.yizhipin.base.ext.loadUrl
import com.yizhipin.base.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/**
 *  Banner图片加载器
 */
class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.loadUrl(path.toString())
    }
}
