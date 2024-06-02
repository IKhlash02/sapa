package com.example.sapa.model

data class Stage(
    val id: Int,
    val noStage: Int,
)

data class Unit(
    val id: Int,
    val unitNo: Int,
    val namaTopik: String,
    val stages: List<Stage>
)
