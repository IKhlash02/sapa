package com.example.sapa.ui.screen.question

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sapa.data.StageRepository
import com.example.sapa.data.remote.response.QuestionsItem
import com.example.sapa.ui.screen.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class QuestionViewModel(
    private val repository: StageRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<QuestionsItem>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<QuestionsItem>>>
        get() = _uiState

    fun getDetailStages(id: Int) {
        viewModelScope.launch {
            repository.getDetailStage(id)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { stages ->
                    Log.d("stages", " viewMode: $stages")
                    _uiState.value = stages
                }
        }
    }
}