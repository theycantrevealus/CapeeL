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
    val nama : String

)
