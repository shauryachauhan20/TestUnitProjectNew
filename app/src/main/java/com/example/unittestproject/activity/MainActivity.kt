package com.example.unittestproject.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapter.TestAdapter
import com.example.unittestproject.R
import com.example.unittestproject.databinding.ActivityMainBinding
import com.example.unittestproject.model.ApiResponseItem
import com.example.unittestproject.util.ViewState
import com.example.unittestproject.viewModel.CatApiRepo
import com.example.unittestproject.viewModel.TestViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: TestAdapter
    private lateinit var testViewModel: TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        testViewModel = TestViewModel(CatApiRepo())

        getApiResponse()
    }

    private fun getApiResponse() {
        binding.progressBar.visibility = View.VISIBLE
        testViewModel.getItemObserver().observe(this, Observer {

            binding.progressBar.visibility = View.GONE

            when (it) {
                is ViewState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is ViewState.Content -> {
                    binding.progressBar.visibility = View.GONE
                    setAdapter(it.apiResponse)
                }

                is ViewState.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        })

        testViewModel.getApiCallItem(10)
    }

    private fun setAdapter(list: ArrayList<ApiResponseItem>) {
        adapter = TestAdapter()
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.catRecyclertview.layoutManager = layoutManager
        binding.catRecyclertview.adapter = adapter

        adapter.setDataList(list)

    }
}