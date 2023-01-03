package com.demoproject.network

import com.demoproject.model.PicSumResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface DemoApi {

    @GET
    suspend fun getImageList(
       @Url endUrl: String?
    ): Response<PicSumResponse>



}