package com.example.sapa.model

import com.example.sapa.data.remote.response.DataItem

data class UnitModel(
    val id: Int,
    val unitNo: Int,
    val namaTopik: String,
    val stages: List<DataItem>
)
