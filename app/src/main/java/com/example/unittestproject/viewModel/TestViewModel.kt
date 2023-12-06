package com.example.unittestproject.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unittestproject.util.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestViewModel(val catApiRepo: CatApiRepo) : ViewModel() {
    private val _viewState = MutableLiveData<ViewState>()
    val apiLiveData = MutableLiveData<ViewState>()
    val viewState: MutableLiveData<ViewState> get() = _viewState

    fun getItemObserver(): MutableLiveData<ViewState> {
        return viewState
    }

    fun getApiCallItem(limit: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = catApiRepo.catData(limit)
            //val response=apiService.getApiData(limit)
            _viewState.postValue(ViewState.Content(response))
        }
    }
}