package com.example.medancapilpelaporan.data.source.remote.response.detail

import com.google.gson.annotations.SerializedName

data class LaporanPindahDetail(

    @field:SerializedName("id")
    override val id: Int,

    @field:SerializedName("id_jenis")
    override val idJenis: Int,

    @field:SerializedName("nama_jenis")
    override val namaJenis: String,

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

    ): ILaporanDetail
