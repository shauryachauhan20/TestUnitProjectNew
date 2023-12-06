package com.example.unittestproject.network

import com.example.unittestproject.model.ApiResponse
import retrofit2.http.*


interface ItemService {
    @GET("search")
    suspend fun getApiData(@Query("limit") limit: Int?): ApiResponse
}