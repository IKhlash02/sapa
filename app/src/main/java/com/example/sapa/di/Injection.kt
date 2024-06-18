package com.example.sapa.di

import android.content.Context
import com.example.sapa.data.StageRepository
import com.example.sapa.data.UserRepository
import com.example.sapa.data.pref.UserPreference
import com.example.sapa.data.pref.dataStore
import com.example.sapa.data.remote.retrofit.ApiConfig

object Injection {

    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }

    fun provideStageRepository(): StageRepository {
        val apiService = ApiConfig.getApiService()
        return StageRepository.getInstance(apiService)
    }
}