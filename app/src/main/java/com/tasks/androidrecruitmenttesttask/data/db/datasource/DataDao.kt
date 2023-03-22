package com.tasks.androidrecruitmenttesttask.data.db.datasource

import com.tasks.androidrecruitmenttesttask.data.db.model.DataEntity
import kotlinx.coroutines.flow.Flow

interface DataDao {
    suspend fun insertData(data: DataEntity)
    suspend fun getAllData(): Flow<List<DataEntity>>
    suspend fun update(data: DataEntity)
}