package com.example.easyshop

import android.os.Parcel
import android.os.Parcelable

data class CategoryList(
    val albumIV:Int,
    val categoryTitle:String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(albumIV)
        parcel.writeString(categoryTitle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CategoryList> {
        override fun createFromParcel(parcel: Parcel): CategoryList {
            return CategoryList(parcel)
        }

        override fun newArray(size: Int): Array<CategoryList?> {
            return arrayOfNulls(size)
        }
    }
}
