package com.example.medancapilpelaporan.data.source.remote.response.detail

import com.google.gson.annotations.SerializedName

data class DetailLahirPelaporanResult (
    @field:SerializedName("response_package")
    override val responsePackage: DetailLahirPelaporanPackage,

    @field:SerializedName("token")
    override val token: String? = null
): IDetailResult

data class DetailLahirPelaporanPackage(

    @field:SerializedName("response_message")
    val responseMessage: String,

    @field:SerializedName("response_result")
    val responseResult: Int,

    @field:SerializedName("response_data")
    val responseData: ArrayList<LaporanLahirDetail>

)

