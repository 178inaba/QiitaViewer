package com.timersinc.trainning.qiitaviewer

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainViewModel(application: Application): AndroidViewModel(application) {
    fun getItems() {
        val qiitaToken = "dd55ce34615cf1c20813f021dbe61ad3e68bcf4f"
        val client = OkHttpClient.Builder()
            .addInterceptor{chain ->
                val newReq = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer $qiitaToken")
                    .build()
                return@addInterceptor chain.proceed(newReq)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://qiita.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()

        val api = retrofit.create(QiitaApiService::class.java)

        api.getItems(1).enqueue(object : Callback<List<Item>> {
            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                val items = response.body() //: List<Item>?
                items?.map { item ->  Log.d("test", item.title)}
            }
        })
    }
}