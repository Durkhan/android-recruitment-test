package com.tasks.androidrecruitmenttesttask.domain.db_usecases


import com.tasks.androidrecruitmenttesttask.data.db.model.DataEntity
import com.tasks.androidrecruitmenttesttask.domain.repository.DataRepository
import javax.inject.Inject

class UpdateDatabaseUseCase @Inject constructor(
    private var dataRepository: DataRepository
) {
    suspend operator fun invoke(dataEntity: DataEntity){
        return dataRepository.updateData(dataEntity)
    }
}