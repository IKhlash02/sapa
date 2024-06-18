package com.example.sapa.data.remote.response

import com.google.gson.annotations.SerializedName

data class StageAllResponse(

	@field:SerializedName("data")
	val data: List<DataItem>,

	@field:SerializedName("message")
	val message: String
)

data class DataItem(

	@field:SerializedName("stage_id")
	val stageId: Int,

	@field:SerializedName("name")
	val name: String
)
