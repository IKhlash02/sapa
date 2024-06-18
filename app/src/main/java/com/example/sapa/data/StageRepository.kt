package com.example.sapa.data

import android.util.Log
import com.example.sapa.data.remote.response.QuestionsItem
import com.example.sapa.data.remote.retrofit.ApiService

import com.example.sapa.model.UnitModel
import com.example.sapa.ui.screen.common.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StageRepository(
    private val apiService: ApiService,
) {
    fun getAllStage(): Flow<UiState<List<UnitModel>>> = flow {
        emit(UiState.Loading)
        Log.d("stageApi", "start")
        try {
            Log.d("stageApi", "start api")
            val response = apiService.getAllRestaurant()
            val stages = response.data
            val newList = stages.chunked(4).mapIndexed { index, chunk ->
                UnitModel(
                    id = index + 1,
                    unitNo = index + 1,
                    namaTopik = "Topik ${index + 1}",
                    stages = chunk
                )
            }.toMutableList()

            Log.d("stageApi", "$newList")
            emit(UiState.Success(newList))
        } catch (e: Exception) {
            Log.d("stageApi", "error: $e")
            emit(UiState.Error(e.message.toString()))
        }
    }

    fun getDetailStage(id: Int): Flow<UiState<List<QuestionsItem>>> = flow {
        emit(UiState.Loading)
        Log.d("stageApi", "start detail")
        try {
            Log.d("stageApi", "start api detail")
            val response = apiService.getDetailRestaurant(id)
            val questions = response.data.questions
            emit(UiState.Success(questions))
        } catch (e: Exception) {
            Log.d("stageApi", "error: $e")
            emit(UiState.Error(e.message.toString()))
        }

    }

    companion object {
        @Volatile
        private var instance: StageRepository? = null
        fun getInstance(
            apiService: ApiService
        ): StageRepository =
            instance ?: synchronized(this) {
                instance ?: StageRepository(apiService)
            }.also { instance = it }
    }
}