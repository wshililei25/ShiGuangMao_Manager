package com.qi.management.store.production_management.dagger

import com.qi.management.store.production_management.view.ProductionListView
import com.yizhipin.base.injection.PerComponentScope
import dagger.Module
import dagger.Provides

/**
 * Creator Qi
 * Date 2019/2/26
 */
@Module
class ProductionListModule(val view: ProductionListView) {

    @PerComponentScope
    @Provides
    fun provideProductionListView(): ProductionListView {
        return view
    }
}