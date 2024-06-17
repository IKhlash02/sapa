package com.example.sapa.data.remote.retrofit

import com.example.sapa.data.remote.response.StageDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("stage/{id}")
    fun getDetailRestaurant(
        @Path("id") id: String
    ): Call<StageDetailResponse>

    @GET("stage/")
    fun getAllRestaurant(
        @Path("id") id: String
    ): Call<StageDetailResponse>
}