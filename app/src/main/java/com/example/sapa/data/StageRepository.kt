package com.example.sapa.data
import android.util.Log
import com.example.sapa.model.FakeAllStage
import com.example.sapa.model.Stage
import com.example.sapa.model.UnitModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class StageRepository {
    private var listStage = mutableListOf<UnitModel>()

    init {
        if (listStage.isEmpty()) {
            val stages = FakeAllStage.dumyStages.map { Stage(it.stageId, it.name) }
            listStage = stages.chunked(4).mapIndexed { index, chunk ->
                UnitModel(
                    id = index + 1,
                    unitNo = index + 1,
                    namaTopik = "Topik ${index + 1}",
                    stages = chunk
                )
            }.toMutableList()

            Log.d("stages", " repository: $listStage")
        }


    }

    fun getAllStage(): Flow<List<UnitModel>> {
        return flowOf(listStage)
    }

    companion object {
        @Volatile
        private var instance: StageRepository? = null
        fun getInstance(): StageRepository =
            instance ?: synchronized(this) {
                instance ?: StageRepository()
            }.also { instance = it }
    }
}