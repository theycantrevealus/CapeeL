package com.example.medancapilpelaporan.di

import android.app.Application
import android.content.Context
import com.example.medancapilpelaporan.data.source.PelaporanRepository
import com.example.medancapilpelaporan.data.source.remote.network.ApiConfig
import com.example.medancapilpelaporan.utils.general.GeneralUtils
import com.example.medancapilpelaporan.utils.general.SessionManager

object Injection {

    fun provideRepository(application: Application): PelaporanRepository {
        return PelaporanRepository.getInstance(ApiConfig.provideApiService(application.applicationContext), application)
    }

}