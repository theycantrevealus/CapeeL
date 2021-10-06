package com.example.medancapilpelaporan.data.source.remote.network

import com.example.medancapilpelaporan.Config
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfig {

    private fun provideOkHttpClient(token: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder().addHeader("Authorization", "Bearer $token").build()
                val response =  chain.proceed(newRequest)
                response
            }
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    fun provideApiService(token: String): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Config.serverAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient(token))
            .build()
        return retrofit.create(ApiService::class.java)
    }
}