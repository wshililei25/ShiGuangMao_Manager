package com.yizhipin.base.data.response

import com.contrarywind.interfaces.IPickerViewData

/**
 * Creator Qi
 * Date 2019/2/17
 */
data class ProvinceBean(
    val city: List<City>,
    val name: String
) : IPickerViewData {
    override fun getPickerViewText(): String {
        return name
    }

    fun getCityList():List<City>{
        return city
    }
}

data class City(
    val area: List<String>,
    val name: String
)