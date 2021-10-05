package com.example.medancapilpelaporan.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LaporMatiResponse(
    @field:SerializedName("nik")
    val nik: String,

    @field:SerializedName("nama_lengkap")
    val namaLengkap: String,

    @field:SerializedName("tempat_lahir")
    val tempatLahir: String,

    @field:SerializedName("tanggal_lahir")
    val tanggalLahir: String,

    @field:SerializedName("tempat_meninggal")
    val tempatMeninggal: String,

    @field:SerializedName("tanggal_meninggal")
    val tanggalMeninggal: String,

    @field:SerializedName("jam_meninggal")
    val jamMeninggal: String,

    @field:SerializedName("nama_keluarga")
    val namaKeluarga: String,

    @field:SerializedName("no_handphone_keluarga")
    val noHpKeluarga: String,

    @field:SerializedName("jenis")
    val jenis: Int = 1
)
