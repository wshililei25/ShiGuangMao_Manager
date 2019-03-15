package com.yizhipin.teacher.mine.note

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.yizhipin.R
import com.yizhipin.base.ui.activity.BaseMvpActivity
import com.yizhipin.provider.router.RouterPath

import com.yizhipin.teacher.mine.note.mvp.NoteContract
import com.yizhipin.teacher.mine.note.mvp.NotePresenterImpl
import kotlinx.android.synthetic.main.activity_note.*

/**
 * Creator Qi
 * Date 2018/12/30
 * <p>新手帮助</p>
 */
@Route(path = RouterPath.UserCenter.NOTE)
class NoteActivity : BaseMvpActivity<NotePresenterImpl>(), NoteContract.IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
//        titleView.setOnLeftIconClickListener { onBackPressed() }
    }

    override fun injectComponent() {

    }

    companion object {
        fun startActivity(fragment: Fragment) {
            val intent = Intent(fragment.context, NoteActivity::class.java)
            fragment.startActivity(intent)
        }
    }

}