package com.qi.management.store.costume.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qi.management.R
import com.qi.management.bean.CostumeCategoryBean
import com.qi.management.store.costume.list.dagger.CostumeListModule
import com.qi.management.store.costume.list.dagger.DaggerCostumeListComponent
import com.qi.management.store.costume.list.presenter.CostumeListPresenterImpl
import com.qi.management.store.costume.list.view.CostumeListActivity.Param.SEX
import com.qi.management.store.costume.list.view.CostumeListActivity.Param.sell_TYPE
import com.qi.management.store.wedding_photography.comboslist.adapter.CombosPagerAdapter
import com.yizhipin.base.ui.fragment.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_costume_list.*

/**
 * Creator Qi
 * Date 2019/2/28
 */
class CostumeListFragment : BaseMvpFragment<CostumeListPresenterImpl>(), CostumeListView {

    override fun injectComponent() {
        DaggerCostumeListComponent.builder()
                .activityComponent(mActivityComponent)
                .costumeListModule(CostumeListModule(this))
                .build()
                .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_costume_list, null)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mBasePresenter.sellType = arguments!!.getInt(sell_TYPE)
        mBasePresenter.sex = arguments!!.getInt(SEX)
        mBasePresenter.getCostumeCategoryList()
    }

    fun setParams(sellType: Int, sex: Int) {
        val bundle = Bundle()
        bundle.putInt(sell_TYPE, sellType)
        bundle.putInt(SEX, sex)
        arguments = bundle
    }

    override fun showCategory(categoryList: List<CostumeCategoryBean>) {
        val pageItemList = arrayListOf<CombosPagerAdapter.CombosPagerItem>()
        for (categoryBean in categoryList) {
            tabLayout.addTab(tabLayout.newTab().setText(categoryBean.name))
            val item = CombosPagerAdapter.CombosPagerItem(CostumeListChildFragment().setParams(mBasePresenter.sellType, mBasePresenter.sex, categoryBean.id), categoryBean.name)
            pageItemList.add(item)
        }
        viewPager.adapter = CombosPagerAdapter(childFragmentManager, pageItemList)
        tabLayout.setupWithViewPager(viewPager)
    }

}