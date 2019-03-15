package com.qi.management.store.costume.list.dagger

import com.qi.management.store.costume.list.view.CostumeListView
import com.yizhipin.base.injection.PerComponentScope
import dagger.Module
import dagger.Provides

/**
 *
 * Creator Qi
 * Date 2019/2/28
 */
@Module
class CostumeListModule(val view: CostumeListView) {
    @Provides
    @PerComponentScope
    fun provideCostumeListView(): CostumeListView {
        return view
    }
}