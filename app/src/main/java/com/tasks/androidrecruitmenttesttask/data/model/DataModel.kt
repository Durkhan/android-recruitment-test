package com.tasks.androidrecruitmenttesttask.data.model

import com.google.gson.annotations.SerializedName

data class DataModel(
    @field:SerializedName("result")
    val result: List<DataResponse>
)