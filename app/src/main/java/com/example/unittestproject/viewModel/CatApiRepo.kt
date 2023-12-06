package com.example.unittestproject.viewModel

import com.example.unittestproject.model.ApiResponse
import com.example.unittestproject.network.RetrofitBuilder.apiService
import kotlinx.coroutines.delay

class CatApiRepo {
    suspend fun catData(limit: Int): ApiResponse {
        delay(3000)

        if (limit == 10) {
            val response = apiService.getApiData(limit)
            return response
        }
        throw IllegalAccessException("Input is incorrect!")
    }
}