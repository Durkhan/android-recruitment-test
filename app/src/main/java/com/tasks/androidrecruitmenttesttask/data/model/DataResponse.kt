package com.tasks.androidrecruitmenttesttask.data.model

import com.google.gson.annotations.SerializedName

data class DataResponse(

	@SerializedName("0")
	var up_down : String,

	@SerializedName("1")
	val brand: String,

	@SerializedName("2")
	val openingPrice: String,

	@SerializedName("3")
	val currentPrice: String,

	@SerializedName("4")
	val lowPrice: String,

	@SerializedName("5")
	val highPrice : String,

	@SerializedName("6")
	val valueOfSharesInDay: Int,

	@SerializedName("7")
	val date: String? = null
)
