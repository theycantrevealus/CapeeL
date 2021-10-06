package com.example.medancapilpelaporan.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LaporPindahResponse(

    @field:SerializedName("nik")
    val nik: String,

    @field:SerializedName("nama")
    val nama: String,

    @field:SerializedName("alamat")
    val alamat: String,

    @field:SerializedName("jenis_pindah")
    val jenisPindah: String,

    @field:SerializedName("status_keluarga")
    val statusKeluarga: String,

    @field:SerializedName("jenis")
    val jenis: Int = 3,

    @field:SerializedName("tgl_submit")
    val tglSubmit: String? = null,
)
