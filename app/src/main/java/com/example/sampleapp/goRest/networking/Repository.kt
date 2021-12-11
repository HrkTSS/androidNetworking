package com.example.sampleapp.goRest.networking

import android.content.Context
import android.util.Log
import com.android.volley.Request.Method.*
import com.example.sampleapp.goRest.Common
import com.example.sampleapp.goRest.Constant.TAG
import com.example.sampleapp.goRest.Constant.USERS
import com.example.sampleapp.goRest.Individual
import com.example.sampleapp.goRest.UserData
import com.google.gson.Gson
import org.json.JSONObject

class Repository : DataSource {
    private val networking = Networking()

    override fun fetchUsers(
        context: Context,
        callback: (Common?, Common?) -> Unit
    ) {
        networking.doNetworkCall(context, GET, "$USERS?page=66") { response, error ->
            if (response != null && error == null) {
                val data = Gson().fromJson(response, Common::class.java)
                callback(data, null)
            } else callback(null, error)
        }
    }

    override fun postUsers(
        context: Context,
        requestBody: UserData,
        callback: (Individual?, Common?) -> Unit
    ) {
        val jsonObj = JSONObject()
        jsonObj.put("name", requestBody.name)
        jsonObj.put("email", requestBody.email)
        jsonObj.put("gender", requestBody.gender)
        jsonObj.put("status", requestBody.status)
        networking.doNetworkCall(context, POST, USERS, jsonObj.toString()) { response, error ->
            if (response != null && error == null) {
                val json = Gson().fromJson(response, Individual::class.java)
                callback(json, null)
            } else callback(null, error)
        }
    }

    override fun updateUser(
        context: Context,
        requestBody: UserData,
        callback: (Individual?, Common?) -> Unit
    ) {
        val jsonObj = JSONObject()
        jsonObj.put("id",requestBody.id)
        if (!requestBody.name.isNullOrEmpty()) jsonObj.put("name", requestBody.name)
        if (!requestBody.email.isNullOrEmpty()) jsonObj.put("email", requestBody.email)
        if (!requestBody.gender.isNullOrEmpty()) jsonObj.put("gender", requestBody.gender)
        if (!requestBody.status.isNullOrEmpty()) jsonObj.put("status", requestBody.status)
        networking.doNetworkCall(context, PUT, "$USERS/${requestBody.id}", jsonObj.toString()) { response, error ->
            if (response != null && error == null) {
                val json = Gson().fromJson(response, Individual::class.java)
                callback(json, null)
            } else callback(null, error)
        }
    }


}