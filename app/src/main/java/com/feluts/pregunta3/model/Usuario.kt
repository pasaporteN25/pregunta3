package com.feluts.pregunta3.model

import android.os.Parcel
import android.os.Parcelable
//import kotlinx.android.parcel.Parcelize


//@Parcelize
data class Usuario(
    val usuario:String,
    val pass:String
):Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }
}