package com.yizhipin.teacher.mine.attention.teacher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.teacher.mine.attention.teacher.mvp.TeacherContract
import com.yizhipin.teacher.mine.attention.teacher.mvp.TeacherPresenterImpl

/**
 * Creator Qi
 * Date 2018/12/30
 */
class TeacherFragment : BaseMvpFragment<TeacherPresenterImpl>(), TeacherContract.IView {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return View.inflate(context, R.layout.fragment_teacher,null)
    }

    override fun injectComponent() {

    }


}