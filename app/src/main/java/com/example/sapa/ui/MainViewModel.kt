package com.example.sapa.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sapa.data.UserRepository
import com.example.sapa.data.pref.UserModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val userRepository: UserRepository ): ViewModel() {

    private val _userData = MutableStateFlow(UserModel("", 0, 0, 5, 1))
    val userData = _userData.asStateFlow()

    init {
        viewModelScope.launch {
            userRepository.getUserData().collect { userModel ->
                _userData.value = userModel
            }
        }
    }

    fun decreaseHeart() {
        val currentHearts = userData.value.heart
        if (currentHearts > 0) {
            val newHearts = currentHearts - 1
            viewModelScope.launch {
               userRepository.updateUserHeart(newHearts)
            }
        }
    }

    fun increaseHeart() {
        val currentHearts = userData.value.heart
            val newHearts = currentHearts + 1
            viewModelScope.launch {
                userRepository.updateUserHeart(newHearts)
            }
    }

    fun increasePoint() {
        val currentPoint = userData.value.point
            val newPoint = currentPoint + 30
            viewModelScope.launch {
                userRepository.updateUserPoint(newPoint)
            }
    }

    fun decreasePoint() {
        val currentPoint = userData.value.point
        if(currentPoint >= 30){
            val newPoint = currentPoint - 30
            viewModelScope.launch {
                userRepository.updateUserPoint(newPoint)
            }
        }
    }

    fun increaseCompleted(stage: Int) {
        viewModelScope.launch {
            userRepository.updateUserCompleted(stage)
        }
    }



    fun saveUserData(name: String, level: Int, points: Int, hearts: Int, completed: Int) {
        viewModelScope.launch {
            userRepository.saveUserData(UserModel(name, level, points, hearts, completed))
        }
    }


}