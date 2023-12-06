package com.example.unittestproject.util

import com.example.unittestproject.model.ApiResponse

sealed class ViewState{
    object Loading : ViewState()
    data class Error(val message: String) : ViewState()
    data class Content(val apiResponse: ApiResponse) : ViewState()
}
