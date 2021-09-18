package com.example.medancapilpelaporan.utils.general

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetroInstance {
    companion object {
        init {

        }

        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
            //this.level = HttpLoggingInterceptor.Level.NONE
        }

        open fun getRetrofitInstance(target: String, token: String? = ""): Retrofit {

            val client: OkHttpClient = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
                this.addInterceptor{
                        chain ->
                    val newRequest = chain.request().newBuilder().addHeader("Authorization", "Bearer " + token).build()
                    val response =  chain.proceed(newRequest)
                    //Log.e("TANAKA", "GLOBAL : " + response.body!!.string())
                    response
                }
            }.build()

            return Retrofit.Builder()
                .baseUrl(target)
                .client(client)
                //.addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}