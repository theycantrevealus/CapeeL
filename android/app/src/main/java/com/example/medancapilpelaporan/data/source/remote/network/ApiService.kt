package com.example.medancapilpelaporan.data.source.remote.network

import com.example.medancapilpelaporan.data.source.remote.response.LaporMatiResponse
import com.example.medancapilpelaporan.data.source.remote.response.ResultResponse
import com.example.medancapilpelaporan.ui.system.LoginActivity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @Headers("Accept: application/json")
    @POST("Pegawai")
    @FormUrlEncoded
    fun signin(
        @Field("request") request: String,
        @Field("username") email: String,
        @Field("password") password: String
    ): Call<LoginActivity.Login>

    @Headers("Accept: application/json")
    @POST("Pelaporan")
    @FormUrlEncoded
    suspend fun kirimLaporan(
        @Field("request") request: String = "tambah_pelaporan",
        @Field("data") data: String
    ): Response<ResultResponse>

}