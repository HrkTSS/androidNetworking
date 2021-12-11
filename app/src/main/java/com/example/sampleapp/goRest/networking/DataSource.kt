package com.example.sampleapp.goRest.networking

import android.content.Context
import com.example.sampleapp.goRest.Common
import com.example.sampleapp.goRest.Individual
import com.example.sampleapp.goRest.UserData

interface DataSource {
    fun fetchUsers(
        context: Context,
        callback: (Common?, Common?) -> Unit
    )

    fun postUsers(
        context: Context,
        requestBody: UserData,
        callback: (Individual?, Common?) -> Unit
    )

    fun updateUser(context: Context,requestBody: UserData,callback: (Individual?, Common?) -> Unit)
}