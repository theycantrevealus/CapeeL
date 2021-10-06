package com.example.medancapilpelaporan.data.source.remote.network

import com.example.medancapilpelaporan.data.source.remote.response.ResultResponse
import com.example.medancapilpelaporan.data.source.remote.response.GetHistoryResultResponse
import com.example.medancapilpelaporan.data.source.remote.response.detail.DetailLahirPelaporanResult
import com.example.medancapilpelaporan.data.source.remote.response.detail.DetailMatiPelaporanResult
import com.example.medancapilpelaporan.data.source.remote.response.detail.DetailPindahPelaporanResult
import com.example.medancapilpelaporan.ui.system.LoginActivity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("Pelaporan")
    suspend fun getHistory(): Response<GetHistoryResultResponse>

    @GET("Pelaporan/detail/{id}")
    suspend fun getDetailLaporanMati(@Path("id") idHistory: Int): Response<DetailMatiPelaporanResult>

    @GET("Pelaporan/detail/{id}")
    suspend fun getDetailLaporanLahir(@Path("id") idHistory: Int): Response<DetailLahirPelaporanResult>

    @GET("Pelaporan/detail/{id}")
    suspend fun getDetailLaporanPindah(@Path("id") idHistory: Int): Response<DetailPindahPelaporanResult>

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

    @Headers("Accept: application/json")
    @POST("Pelaporan")
    @FormUrlEncoded
    suspend fun hapusLaporan(
        @Field("request") request: String = "delete_pelaporan",
        @Field("id") id: Int
    ): Response<ResultResponse>

}