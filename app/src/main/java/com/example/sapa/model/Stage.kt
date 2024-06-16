package com.example.sapa.model

data class Stage(
    val id: Int,
    val name: String,
    var isComplated: Boolean = false,
)

data class Stage1(
    val stageId: Int,
    val name: String,
)

data class UnitModel(
    val id: Int,
    val unitNo: Int,
    val namaTopik: String,
    val stages: List<Stage>
)

data class StageDetail(
    val question_id: Int,
    val Image: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val answer: String
)
