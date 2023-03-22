package com.tasks.androidrecruitmenttesttask.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.tasks.androidrecruitmenttesttask.common.SocketData
import com.tasks.androidrecruitmenttesttask.data.model.DataResponse
import com.tasks.androidrecruitmenttesttask.domain.LoadDataUseCase
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
) : ViewModel() {

    private val _webSocketData: MutableStateFlow<SocketData<List<DataResponse>>?> = MutableStateFlow(null)
    val webSocketData: StateFlow<SocketData<List<DataResponse>>?> = _webSocketData.asStateFlow()

    fun connect() {
        CoroutineScope(Dispatchers.IO).launch{
            loadDataUseCase().collect { data ->
                _webSocketData.value = data
            }
        }
    }



}
