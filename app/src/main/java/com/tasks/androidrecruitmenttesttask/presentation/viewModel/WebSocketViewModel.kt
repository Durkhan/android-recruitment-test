package com.tasks.androidrecruitmenttesttask.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.tasks.androidrecruitmenttesttask.common.SocketData
import com.tasks.androidrecruitmenttesttask.data.db.model.DataEntity
import com.tasks.androidrecruitmenttesttask.data.model.DataResponse
import com.tasks.androidrecruitmenttesttask.domain.LoadDataUseCase
import com.tasks.androidrecruitmenttesttask.domain.db_usecases.GetAllDataUseCase
import com.tasks.androidrecruitmenttesttask.domain.db_usecases.InsertDataUseCases
import com.tasks.androidrecruitmenttesttask.domain.db_usecases.UpdateDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WebSocketViewModel @Inject constructor(
    private var loadDataUseCase: LoadDataUseCase,
    private var insertDataUseCases: InsertDataUseCases,
    private var getAllDataUseCase: GetAllDataUseCase,
    private var clearDatabaseUseCase: UpdateDatabaseUseCase
) : ViewModel() {

    private val _webSocketData: MutableStateFlow<SocketData<List<DataResponse>>?> = MutableStateFlow(null)
    val webSocketData: StateFlow<SocketData<List<DataResponse>>?> = _webSocketData.asStateFlow()

    private val _localData: MutableStateFlow<List<DataEntity>?> = MutableStateFlow(null)
    val localData: StateFlow<List<DataEntity>?> = _localData.asStateFlow()

    fun connect() {
        CoroutineScope(Dispatchers.IO).launch{
            loadDataUseCase().collect { data ->
                _webSocketData.value = data
            }
        }
    }

    fun getAllData() {
        CoroutineScope(Dispatchers.IO).launch{
            getAllDataUseCase().collect{ data->
                _localData.value = data
            }
        }
    }

    fun insertData(data: DataEntity) {
        CoroutineScope(Dispatchers.IO).launch{
            insertDataUseCases(data)
        }
    }

    fun updateDatabase(data: DataEntity){
        CoroutineScope(Dispatchers.IO).launch{
            clearDatabaseUseCase(data)
        }
    }



}
