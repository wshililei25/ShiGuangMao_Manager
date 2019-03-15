package com.yizhipin.ui.fragment

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import com.qi.management.ui.activity.*
import com.yizhipin.R
import com.yizhipin.base.data.response.GridItem
import com.yizhipin.base.recyclerview.GridItemDecoration
import com.yizhipin.base.ui.adapter.BaseRecyclerViewAdapter
import com.yizhipin.base.ui.fragment.BaseFragment
import com.yizhipin.ui.adapter.PersonncelAdapter
import kotlinx.android.synthetic.main.fragment_home_financial_affairs.*
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by ${XiLei} on 2018/8/19.
 * 财务管理
 */
class FinancialAffairsFragment : BaseFragment() {

    private lateinit var mAdapter: PersonncelAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_financial_affairs, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        mAdapter = PersonncelAdapter(activity!!)
        var list = mutableListOf<GridItem>()
        list.add(GridItem(R.drawable.clipboard, getString(R.string.order_management)))
        list.add(GridItem(R.drawable.wallet, getString(R.string.withdraw_cash_apply)))
        list.add(GridItem(R.drawable.customerservice, getString(R.string.complaint_recommendations)))
        list.add(GridItem(R.drawable.refund, getString(R.string.refund_audit)))
        list.add(GridItem(R.drawable.paperplane, getString(R.string.prope_management)))
        list.add(GridItem(R.drawable.pie_chart, getString(R.string.financial_statistics)))
        mAdapter.dataList = list

        mRv.addItemDecoration(GridItemDecoration(context))
        mRv.layoutManager = GridLayoutManager(context, 3, GridLayout.VERTICAL, false)
        mRv.adapter = mAdapter
        mAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<GridItem> {
            override fun onItemClick(item: GridItem, position: Int) {
                when (position) {
                    0 -> startActivity<ManagerOrderActivity>()
                    1 -> startActivity<WithdrawCashApplyActivity>()
                    2 -> startActivity<ComplainActivity>()
                    3 -> startActivity<RefundApplyActivity>()
                    4 -> startActivity<NewsActivity>()
                    5 -> startActivity<FinancialStatisticsActivity>()
                }
            }
        })
    }

}