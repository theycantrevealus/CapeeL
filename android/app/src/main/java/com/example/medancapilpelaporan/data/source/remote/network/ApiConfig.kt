package com.example.medancapilpelaporan.data.source.remote.network

import android.content.Context
import com.example.medancapilpelaporan.Config
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

    private fun provideOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(AuthInterceptor(context))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    fun provideApiService(context: Context): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Config.serverAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient(context))
            .build()
        return retrofit.create(ApiService::class.java)
    }
}