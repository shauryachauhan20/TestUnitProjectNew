package com.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unittestproject.R
import com.example.unittestproject.databinding.CatItemLayoutBinding
import com.example.unittestproject.model.ApiResponseItem

class TestAdapter : RecyclerView.Adapter<TestAdapter.CatViewHolder>() {
    private var itemList = ArrayList<ApiResponseItem>()

    fun setDataList(list: ArrayList<ApiResponseItem>) {
        this.itemList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CatViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: CatItemLayoutBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.cat_item_layout,
            parent,
            false
        )
        return CatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) =
        with(itemList[position]) {
            holder.bindItem(this)
        }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class CatViewHolder(val binding: CatItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(list: ApiResponseItem) {
           // binding.qtyValue.text = list.currentQty.toString()
            Glide.with(binding.root).load(list.url).into(binding.catImg)
        }
    }
}