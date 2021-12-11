package com.example.sampleapp.goRest.networking

import android.content.Context
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.sampleapp.goRest.Common
import com.example.sampleapp.goRest.Constant.API_TOKEN
import com.example.sampleapp.goRest.Constant.BASE_URL
import com.example.sampleapp.goRest.Constant.TAG
import com.example.sampleapp.goRest.Individual
import com.example.sampleapp.goRest.UserData
import com.google.gson.Gson
import org.json.JSONObject
import java.nio.charset.Charset


class Networking {
    fun doNetworkCall(
        context: Context,
        method: Int,
        endPoint: String,
        requestBody: String? = null,
        callback: (String?, Common?) -> Unit
    ) {
        val queue = Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(method, BASE_URL + endPoint, { response ->
            Log.d(TAG, "Respone >>>> $response")
            callback(response, null)
        }, { error ->
            if (error.networkResponse != null && error.networkResponse.data != null && error.networkResponse.data.isNotEmpty()) {
                val errorData = JSONObject(String(error.networkResponse.data))
                Log.d(TAG, "Error >>>> $errorData")
                if (errorData["data"] is JSONObject) {
                    val data =
                        Gson().fromJson(String(error.networkResponse.data), Individual::class.java)
                    callback(null, Common(listOf(data.data)))
                } else callback(
                    null, Gson().fromJson(
                        String(error.networkResponse.data),
                        Common::class.java
                    )
                )
            } else callback(null, Common(listOf(UserData(field = "unknown", message = "Unknown error"))))
        }) {
            override fun getBodyContentType(): String {
                return "application/json"
            }

            override fun getBody(): ByteArray? {
                return try {
                    if(requestBody!=null) Log.d(TAG, "Request >>>> $requestBody")
                    requestBody?.toByteArray()
                } catch (e: Exception) {
                    null
                }
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = mutableMapOf<String, String>()
                headers["Authorization"] = API_TOKEN
                headers["Accept"] = "application/json"
                return headers
            }
        }
        queue.add(stringRequest)
    }
}