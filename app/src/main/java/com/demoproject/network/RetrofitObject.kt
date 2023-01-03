package com.demoproject.network

import com.demoproject.constants.HelperConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitObject {
    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)

    private val okHttpClient = OkHttpClient().newBuilder()
        .addInterceptor(logger)

    private val retrofit = Retrofit.Builder()
        .baseUrl(HelperConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient.build())
        .build()

    val api: DemoApi by lazy {
        retrofit.create(DemoApi::class.java)
    }

}