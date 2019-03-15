package com.qi.management.injection.module

import com.qi.management.service.ManagerService
import com.qi.management.service.impl.ManagerServiceImpl
import dagger.Module
import dagger.Provides

/**
 * Created by ${XiLei} on 2018/8/4.
 */
@Module
class ManagerModule {

    @Provides
    fun provideManagerService(service: ManagerServiceImpl): ManagerService {
        return service
    }
}