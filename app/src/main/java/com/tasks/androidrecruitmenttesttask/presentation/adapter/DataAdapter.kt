package com.tasks.androidrecruitmenttesttask.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tasks.androidrecruitmenttesttask.data.model.DataResponse
import com.tasks.androidrecruitmenttesttask.databinding.ListOfDataBinding

class DataAdapter(private var data:List<DataResponse> ):RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.DataViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding= ListOfDataBinding.inflate(inflater)
        return DataViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DataAdapter.DataViewHolder, position: Int) {
        with(holder.binding){
            brand.text=data[position].brand
            lowPriceValue.text=data[position].lowPrice
            highPriceValue.text=data[position].highPrice
            currentPriceValue.text=data[position].currentPrice
            openingPriceValue.text=data[position].openingPrice
            total.text="Volume of shared ${data[position].valueOfSharesInDay}"
            brand.isSelected= data[position].up_down == "up"
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class DataViewHolder(val binding:ListOfDataBinding):RecyclerView.ViewHolder(binding.root){}
}