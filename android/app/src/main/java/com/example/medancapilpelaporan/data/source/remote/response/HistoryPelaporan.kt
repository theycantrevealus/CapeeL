package com.example.medancapilpelaporan.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class HistoryPelaporan(

    @field:SerializedName("id")
    val id : Int,

    @field:SerializedName("id_jenis")
    val idJenis : Int,

    @field:SerializedName("nama_jenis")
    val namaJenis : String,

    @field:SerializedName("nik")
    val nik : String,

    @field:SerializedName("nama")
    val nama : String,

    @field:SerializedName("created_at")
    val waktuSubmit : String

)

data class GetHistoryResultResponse(

    @field:SerializedName("response_package")
    val responsePackage: GetHistoryResponsePackage,

    @field:SerializedName("token")
    val token: String? = null
)

data class GetHistoryResponsePackage(

    @field:SerializedName("response_message")
    val responseMessage: String,

    @field:SerializedName("response_result")
    val responseResult: Int,

    @field:SerializedName("response_data")
    val responseData: ArrayList<HistoryPelaporan>

)



