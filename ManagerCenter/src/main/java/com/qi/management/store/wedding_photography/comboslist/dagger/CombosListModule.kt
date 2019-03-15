package com.qi.management.store.wedding_photography.comboslist.dagger

import com.qi.management.store.wedding_photography.comboslist.CombosListContract
import com.yizhipin.base.injection.PerComponentScope
import dagger.Module
import dagger.Provides

/**
 * Creator Qi
 * Date 2019/2/24
 */
@Module
class CombosListModule(val view: CombosListContract.CombosListView) {

    @PerComponentScope
    @Provides
    fun providerCombosListView(): CombosListContract.CombosListView {
        return view
    }
}