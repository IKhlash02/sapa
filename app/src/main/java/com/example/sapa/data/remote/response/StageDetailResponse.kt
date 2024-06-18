package com.example.sapa.data.remote.response

import com.google.gson.annotations.SerializedName

data class StageDetailResponse(

	@field:SerializedName("data")
	val data: Data,

	@field:SerializedName("message")
	val message: String? = null
)

data class Stage(

	@field:SerializedName("stage_id")
	val stageId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class Data(

	@field:SerializedName("stage")
	val stage: Stage? = null,

	@field:SerializedName("questions")
	val questions: List<QuestionsItem>
)

data class QuestionsItem(

	@field:SerializedName("option_4")
	val option4: String? = null,

	@field:SerializedName("option_2")
	val option2: String? = null,

	@field:SerializedName("option_3")
	val option3: String? = null,

	@field:SerializedName("answer")
	val answer: String? = null,

	@field:SerializedName("stage_id")
	val stageId: Int? = null,

	@field:SerializedName("link")
	val link: String? = null,

	@field:SerializedName("question_id")
	val questionId: Int? = null,

	@field:SerializedName("option_1")
	val option1: String? = null
)
