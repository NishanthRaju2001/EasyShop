package com.example.easyshop

import android.os.Parcel
import android.os.Parcelable

data class SmartPhoneList(
    val smartPhoneIV:Int,
    val smartPhoneTitle:String?,
    val smartPhoneDescription:String?,
    var price:Int
):Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(smartPhoneIV)
        parcel.writeString(smartPhoneTitle)
        parcel.writeString(smartPhoneDescription)
        parcel.writeInt(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SmartPhoneList> {
        override fun createFromParcel(parcel: Parcel): SmartPhoneList {
            return SmartPhoneList(parcel)
        }

        override fun newArray(size: Int): Array<SmartPhoneList?> {
            return arrayOfNulls(size)
        }
    }
}
