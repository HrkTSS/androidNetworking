package com.example.sampleapp.goRest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Common(val data: List<UserData>? = null)
data class Individual(val data: UserData)

@Parcelize
data class UserData(
    var id: Int? = null,
    var field: String? = null,
    var name: String? = null,
    var email: String? = null,
    var gender: String? = null,
    var status: String? = null,
    var message: String? = null
) : Parcelable