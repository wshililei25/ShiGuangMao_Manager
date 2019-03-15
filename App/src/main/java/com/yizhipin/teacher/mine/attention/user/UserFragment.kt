package com.yizhipin.teacher.mine.attention.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.teacher.mine.attention.user.mvp.UserContract
import com.yizhipin.teacher.mine.attention.user.mvp.UserPresenterImpl

/**
 * Creator Qi
 * Date 2018/12/30
 */
class UserFragment : BaseMvpFragment<UserPresenterImpl>(), UserContract.IView {

    override fun injectComponent() {

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.fragment_user, null)
    }


}