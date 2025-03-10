package com.example.core.http

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

object HttpClient : OkHttpClient() {
    //    constructor()
//
//    public val httpClient INSTANCE = HttpClient()


    private val gson: Gson = Gson()

    private fun <T> convert(json: String, type: Type): T {
        return gson.fromJson(json, type)

    }


    fun <T> get(path: String, type: Type, entityCallback: EntityCallback<T>) {
        val request = Request.Builder().url("https://api.hencoder.com/$path").build()

        val call = newCall(request)

        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                when (response.code()) {
                    in 200..299 -> {
                        val json = response.body()?.string()
                        entityCallback.onSuccess(convert(json ?: "", type))
                    }
                    in 400..500 -> {
                        entityCallback.onFailure("客户端错误")
                    }
                    in 500..600 -> {
                        entityCallback.onFailure("未知错误")
                    }
                }
            }

        })
    }
}