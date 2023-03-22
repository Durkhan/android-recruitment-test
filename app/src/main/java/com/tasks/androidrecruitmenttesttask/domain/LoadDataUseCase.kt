package com.tasks.androidrecruitmenttesttask.domain


import com.tasks.androidrecruitmenttesttask.common.SocketData
import com.tasks.androidrecruitmenttesttask.data.model.DataResponse
import com.tasks.androidrecruitmenttesttask.data.remote.WebSocketRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadDataUseCase @Inject constructor(private val repository: WebSocketRepository) {
    suspend operator fun invoke(): Flow<SocketData<List<DataResponse>>>{
        return repository.observeWebSocketData()
    }
}