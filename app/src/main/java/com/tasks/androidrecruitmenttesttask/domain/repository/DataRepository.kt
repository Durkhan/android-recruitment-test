package com.tasks.androidrecruitmenttesttask.domain.repository

import com.tasks.androidrecruitmenttesttask.data.db.datasource.DataDao
import com.tasks.androidrecruitmenttesttask.data.db.model.DataEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DataRepository @Inject constructor(private val dao: DataDao) {

    suspend fun insertData(data: DataEntity) = dao.insertData(data)
    suspend fun getAllData(): Flow<List<DataEntity>> =  dao.getAllData()
    suspend fun updateData(data: DataEntity) =  dao.update(data)

}