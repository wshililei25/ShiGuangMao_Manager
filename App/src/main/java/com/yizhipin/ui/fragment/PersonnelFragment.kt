package com.yizhipin.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import com.qi.management.ui.activity.AddStaffActivity
import com.qi.management.ui.activity.CustomerListActivity
import com.qi.management.ui.activity.StaffListActivity
import com.qi.management.ui.activity.TeacherListActivity
import com.yizhipin.R
import com.yizhipin.base.data.response.GridItem
import com.yizhipin.base.recyclerview.GridItemDecoration
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.ui.fragment.BaseFragment
import com.yizhipin.ui.adapter.PersonncelAdapter
import com.yizhipin.usercenter.ui.activity.TeacherEnterDatumActivity
import kotlinx.android.synthetic.main.fragment_home_personnel.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by ${XiLei} on 2018/8/19.
 * 人员管理
 */
class PersonnelFragment : BaseFragment() {

    private lateinit var mAdapter: PersonncelAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_personnel, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mAdapter = PersonncelAdapter(activity!!)
        var list = mutableListOf<GridItem>()
        list.add(GridItem(R.drawable.ic_tie, getString(R.string.teacher_management)))
        list.add(GridItem(R.drawable.bg_add_person, getString(R.string.teacher_apply)))
        list.add(GridItem(R.drawable.student, getString(R.string.staff_management)))
        list.add(GridItem(R.drawable.student2, getString(R.string.staff_add)))
        list.add(GridItem(R.drawable.girl, getString(R.string.client_management)))
        mAdapter.dataList = list

        mRv.addItemDecoration(GridItemDecoration(context))
        mRv.layoutManager = GridLayoutManager(context, 3, GridLayout.VERTICAL, false)
        mRv.adapter = mAdapter
        mAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<GridItem> {
            override fun onItemClick(item: GridItem, position: Int) {
                when (position) {
                    0 -> startActivity<TeacherListActivity>()
                    1 -> startActivity<TeacherEnterDatumActivity>()
                    2 -> startActivity<StaffListActivity>()
                    3 -> startActivity<AddStaffActivity>()
                    4 -> startActivity<CustomerListActivity>()
                }
            }
        })
    }

}