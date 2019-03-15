package com.yizhipin.teacher.schedule.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yizhipin.R
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import com.yizhipin.teacher.schedule.presenter.GrabPresenter
import kotlinx.android.synthetic.main.fragment_grab.*

/**
 * Creator Qi
 * Date 2018/12/4
 */
class GrabFragment : BaseMvpFragment<GrabPresenter>() {
    private lateinit var mTabs: Array<String>
    private var mFragments: ArrayList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTabs = arrayOf(
                context!!.resources.getString(R.string.navAll),
                context!!.resources.getString(R.string.navFollowingShot),
                context!!.resources.getString(R.string.navCamera),
                context!!.resources.getString(R.string.navMakeUp)
        )
        mFragments.add(GrabSubFragment())
        mFragments.add(GrabSubFragment())
        mFragments.add(GrabSubFragment())
        mFragments.add(GrabSubFragment())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_grab, null)
    }

    override fun injectComponent() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mSlidingTabLayout.setViewPager(mViewPager, mTabs, activity, mFragments)
    }
}