package com.qi.management.store.common_detail.dagger

import com.qi.management.store.common_detail.view.CombosDetailView
import com.yizhipin.base.injection.PerComponentScope
import dagger.Module
import dagger.Provides

/**
 * Creator Qi
 * Date 2019/2/24
 */
@Module
class CombosDetailModule(val view: CombosDetailView) {
    @PerComponentScope
    @Provides
    fun provideCombosDetailView(): CombosDetailView {
        return view
    }
}