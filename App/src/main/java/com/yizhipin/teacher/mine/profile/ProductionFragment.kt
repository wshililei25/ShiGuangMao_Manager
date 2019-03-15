package com.yizhipin.teacher.mine.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.teacher.mine.profile.mvp.ProductPresenterImpl

/**
 * Creator Qi
 * Date 2019/1/9
 * <p>我的作品</p>
 */
class ProductionFragment : BaseMvpFragment<ProductPresenterImpl>() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.fragment_product,null)
    }

    override fun injectComponent() {

    }
}