package com.example.sapa.data

import com.example.sapa.data.pref.UserModel
import com.example.sapa.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow


class UserRepository private constructor(
    private val userPreference: UserPreference
) {

    suspend fun saveUserData(user: UserModel) {
        userPreference.saveDataUser(user)
    }

    suspend fun updateUserName(name: String) {
        userPreference.updateUserName(name)
    }

    suspend fun updateUserLevel(level: Int) {
     userPreference.updateUserLevel(level)
    }

    suspend fun updateUserPoint(point: Int) {
      userPreference.updateUserPoint(point)
    }

    suspend fun updateUserHeart(heart: Int) {
       userPreference.updateUserHeart(heart)
    }


    fun getUserData(): Flow<UserModel> {
        return userPreference.getUserData()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference)
            }.also { instance = it }
    }
}
