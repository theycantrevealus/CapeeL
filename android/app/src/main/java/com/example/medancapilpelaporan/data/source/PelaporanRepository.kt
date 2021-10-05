package com.example.medancapilpelaporan.data.source

import android.app.Application
import android.util.Log.d
import com.example.medancapilpelaporan.data.source.remote.network.ApiService
import com.example.medancapilpelaporan.data.source.remote.response.LaporLahirResponse
import com.example.medancapilpelaporan.data.source.remote.response.LaporMatiResponse
import com.example.medancapilpelaporan.data.source.remote.response.LaporPindahResponse
import com.example.medancapilpelaporan.data.source.remote.response.ResultResponse
import com.example.medancapilpelaporan.utils.general.GeneralUtils
import com.google.gson.Gson

class PelaporanRepository private constructor(private val apiService: ApiService, val application: Application) {

    companion object {
        @Volatile
        private var INSTANCE: PelaporanRepository? = null

        fun getInstance(apiService: ApiService, application: Application): PelaporanRepository = INSTANCE ?: synchronized(this) {
            val instance = PelaporanRepository(apiService, application)
            INSTANCE = instance
            instance
        }
    }

    suspend fun postLaporMati(laporMatiResponse: LaporMatiResponse): ResultResponse? {
        val jsonString = Gson().toJson(laporMatiResponse)
        return kirimLaporan(jsonString)
    }

    suspend fun postLaporLahir(laporLahirResponse: LaporLahirResponse): ResultResponse? {
        val jsonString = Gson().toJson(laporLahirResponse)
        return kirimLaporan(jsonString)
    }

    suspend fun postLaporPindah(laporPindahResponse: LaporPindahResponse): ResultResponse? {
        val jsonString = Gson().toJson(laporPindahResponse)
        return kirimLaporan(jsonString)
    }

    suspend fun kirimLaporan(jsonString: String): ResultResponse? {
        val response = apiService.kirimLaporan(data = jsonString)

        return if (response.isSuccessful) {
            if (response.code() == 202) {
                GeneralUtils.updateToken(response.body()?.token, application.applicationContext)
            }
            response.body()
        } else {
            d("ERROR_POST", response.toString())
            null
        }
    }

}