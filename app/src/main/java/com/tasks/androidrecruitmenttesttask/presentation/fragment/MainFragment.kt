package com.tasks.androidrecruitmenttesttask.presentation.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.tasks.androidrecruitmenttesttask.R
import com.tasks.androidrecruitmenttesttask.common.SocketData
import com.tasks.androidrecruitmenttesttask.data.db.model.toDataResponse
import com.tasks.androidrecruitmenttesttask.data.model.DataResponse
import com.tasks.androidrecruitmenttesttask.data.model.toDataEntity
import com.tasks.androidrecruitmenttesttask.databinding.MainFragmentBinding
import com.tasks.androidrecruitmenttesttask.presentation.adapter.DataAdapter
import com.tasks.androidrecruitmenttesttask.presentation.viewModel.WebSocketViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment:Fragment() {

    private var _binding: MainFragmentBinding?=null
    private val binding get() = _binding!!
    private val viewModel: WebSocketViewModel by viewModels()
    private var listOfData= mutableListOf<DataResponse>()
    private lateinit var adapter: DataAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=MainFragmentBinding.inflate(inflater,container,false)
        val layoutManager = GridLayoutManager(context,VERTICAL)
        binding.recycelview.layoutManager = layoutManager
        adapter= DataAdapter(listOfData)
        binding.recycelview.adapter = adapter
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            viewModel.webSocketData.collect { data ->
                when (data) {
                    is SocketData.Connected -> {
                        binding.connected.text=getString(R.string.connected)
                        binding.connected.setTextColor(ContextCompat.getColor(requireContext(),R.color.green))
                        binding.progressBar.isVisible=true
                    }
                    is SocketData.Update -> {
                        showDataFromRemote(data.value)
                        if (databaseIsEmpty()) addDataToDatabase(data.value)
                        else updatedDatabase(data.value)
                        binding.progressBar.isVisible=false

                    }
                    is SocketData.Error -> {
                        binding.progressBar.isVisible=false
                        showDataFromLocal()
                        binding.connected.text=getString(R.string.not_connectced)
                        binding.connected.setTextColor(ContextCompat.getColor(requireContext(),R.color.red))
                    }
                }
            }

        }
        viewModel.connect()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun showDataFromRemote(data: List<DataResponse>) {
        listOfData.clear()
        listOfData.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private fun updatedDatabase(data: List<DataResponse>) {
        data.onEach {
            viewModel.updateDatabase(it.toDataEntity())
        }
    }

    private fun addDataToDatabase(data: List<DataResponse>) {
        data.onEach {
            viewModel.insertData(it.toDataEntity())
        }
    }

    private fun databaseIsEmpty():Boolean{
        viewModel.getAllData()
        return viewModel.localData.value?.isEmpty() == true
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showDataFromLocal() {
        listOfData.clear()
        viewModel.getAllData()
        lifecycleScope.launch {
            viewModel.localData.collect {
                it?.onEach { dataEntity ->
                    listOfData.add(dataEntity.toDataResponse())
                }
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}


