package com.tasks.androidrecruitmenttesttask.domain.db_usecases

import com.tasks.androidrecruitmenttesttask.data.db.model.DataEntity
import com.tasks.androidrecruitmenttesttask.domain.repository.DataRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllDataUseCase @Inject constructor(
    private val dataRepository: DataRepository
){
    suspend operator fun invoke(): Flow<List<DataEntity>>{
        return dataRepository.getAllData()
    }
}