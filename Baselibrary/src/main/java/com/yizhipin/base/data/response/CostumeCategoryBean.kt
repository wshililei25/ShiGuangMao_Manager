package com.qi.management.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Creator Qi
 * Date 2019/3/1
 */
data class CostumeCategoryBean(
    var id: Int,
    var name: String,
    var sex: Int,
    var type: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(sex)
        parcel.writeInt(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CostumeCategoryBean> {
        override fun createFromParcel(parcel: Parcel): CostumeCategoryBean {
            return CostumeCategoryBean(parcel)
        }

        override fun newArray(size: Int): Array<CostumeCategoryBean?> {
            return arrayOfNulls(size)
        }
    }
}