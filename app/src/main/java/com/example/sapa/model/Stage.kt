package com.example.sapa.model

data class Stage(
    val id: Int,
    val noStage: Int?,
    var isCompleted: Boolean = false
)

data class Unit(
    val id: Int,
    val unitNo: Int,
    val namaTopik: String,
    val stages: List<Stage>
)

data class StageDetail(
    val questionId: Int,
    val Image: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val answer: String
)
