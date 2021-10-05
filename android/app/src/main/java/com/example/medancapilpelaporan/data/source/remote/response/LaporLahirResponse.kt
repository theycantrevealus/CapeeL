package com.example.medancapilpelaporan.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LaporLahirResponse(

    @field:SerializedName("nik_ortu")
    val nikOrtu: String,

    @field:SerializedName("nama_ortu")
    val namaOrtu: String,

    @field:SerializedName("tempat_lahir")
    val tempatLahir: String,

    @field:SerializedName("tanggal_lahir")
    val tanggalLahir: String,

    @field:SerializedName("nama_anak")
    val namaAnak: String,

    @field:SerializedName("alamat")
    val alamat: String,

    @field:SerializedName("jenis")
    val jenis: Int = 2
)
