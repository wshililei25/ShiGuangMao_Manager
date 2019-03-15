package com.qi.management.home.stores.controler

import com.qi.management.R
import com.qi.management.store.store_info_management.mvp.StoreInfoManagementPresenterImpl
import com.yizhipin.provider.router.RouterPath

/**
 * Creator Qi
 * Date 2019/1/27
 */
class HomeConfig {
    companion object {
        fun createItemList(style: Int): MutableList<HomeGridItem> {
            return when (style) {
                StoreInfoManagementPresenterImpl.Style.Store -> createStoreItems()
                StoreInfoManagementPresenterImpl.Style.Person -> createPersonItems()
                StoreInfoManagementPresenterImpl.Style.Finance -> createFinanceItems()
                else -> throw Exception("首页不存在该页面样式")
            }
        }

        private fun createFinanceItems(): MutableList<HomeGridItem> {
            return mutableListOf()
        }

        private fun createPersonItems(): MutableList<HomeGridItem> {
            return mutableListOf()
        }

        /**
         * 创建门店首页九宫格Item
         */
        private fun createStoreItems(): MutableList<HomeGridItem> {
            val list = mutableListOf<HomeGridItem>()
            list.add(HomeGridItem(R.string.storeInformationManagement, R.drawable.online_store, RouterPath.Management.STORE_INFORMATION_MANAGEMENT))
            list.add(HomeGridItem(R.string.wedding_photo_studio, R.drawable.ic_homework, RouterPath.Management.PACKAGE_LIST))
            list.add(HomeGridItem(R.string.title_production_manage, R.drawable.ic_product, RouterPath.Management.PRODUCTION_LIST))
            list.add(HomeGridItem(R.string.title_costume_manage, R.drawable.ic_ballgown, RouterPath.Management.COSTUME_PAVILION))
            list.add(HomeGridItem(R.string.title_baby_photography, R.drawable.ic_ballgown, RouterPath.Management.PACKAGE_LIST))
            list.add(HomeGridItem(R.string.title_portrait_photography, R.drawable.ic_ballgown, RouterPath.Management.PACKAGE_LIST))
            return list
        }
    }
}