package com.example.sapa.ui.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sapa.data.StageRepository
import com.example.sapa.model.UnitModel
import com.example.sapa.ui.screen.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: StageRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<UnitModel>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<UnitModel>>>
        get() = _uiState

    fun getAllStages() {
        viewModelScope.launch {
            repository.getAllStage()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { stages ->
                    Log.d("stages", " viewMode: $stages")
                    _uiState.value = UiState.Success(stages)
                }
        }
    }
}