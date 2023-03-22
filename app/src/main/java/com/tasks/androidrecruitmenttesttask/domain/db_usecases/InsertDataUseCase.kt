package com.tasks.androidrecruitmenttesttask.domain.db_usecases

import com.tasks.androidrecruitmenttesttask.data.db.model.DataEntity
import com.tasks.androidrecruitmenttesttask.domain.repository.DataRepository
import javax.inject.Inject

class InsertDataUseCases @Inject constructor(
    private var dataRepository: DataRepository
) {
    suspend  operator fun invoke(dataEntity: DataEntity){
        dataRepository.insertData(dataEntity)
    }
}