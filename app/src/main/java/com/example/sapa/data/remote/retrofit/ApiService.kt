package com.example.sapa.data.remote.retrofit

import com.example.sapa.data.remote.response.StageAllResponse
import com.example.sapa.data.remote.response.StageDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("stage/{id}")
    suspend fun getDetailRestaurant(
        @Path("id") id: Int
    ): StageDetailResponse

    @GET("stage/")
    suspend fun getAllRestaurant(
    ): StageAllResponse
}