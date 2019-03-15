package com.qi.management.store.wedding_photography.comboslist.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.qi.management.store.wedding_photography.comboslist.view.CombosListFragment

/**
 * Creator Qi
 * Date 2019/2/24
 *
 */
class CombosPagerAdapter(fm: FragmentManager, private val items: MutableList<CombosPagerItem>) : FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {
        return items[p0].fragment
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return items[position].title
    }

    data class CombosPagerItem(
            var fragment: Fragment,
            var title: String
    )

}