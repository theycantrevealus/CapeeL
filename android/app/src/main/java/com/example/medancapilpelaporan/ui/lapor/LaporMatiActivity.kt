package com.example.medancapilpelaporan.ui.lapor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.medancapilpelaporan.Config
import com.example.medancapilpelaporan.MainActivity
import com.example.medancapilpelaporan.R
import com.example.medancapilpelaporan.databinding.ActivityLaporMatiBinding
import com.example.medancapilpelaporan.ui.profile.ProfileEditActivity
import com.example.medancapilpelaporan.ui.system.LoginActivity
import com.example.medancapilpelaporan.utils.general.InputUtils
import com.example.medancapilpelaporan.utils.general.RetroInstance
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.github.muddz.styleabletoast.StyleableToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

class LaporMatiActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLaporMatiBinding
    private val configuration: Config = Config()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLaporMatiBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLaporkanMati.setOnClickListener(View.OnClickListener {
            val nik = binding.laporMatiNik.text.toString().trim()
            val nama = binding.laporMatiNama.text.toString().trim()
            val tempat_lahir = binding.laporMatiTempatLahir.text.toString().trim()
            val tanggal_lahir = binding.laporMatiTanggalLahir.text.toString().trim()
            val tempat_meninggal = binding.laporMatiTempatMati.text.toString().trim()
            val tanggal_meninggal = binding.laporMatiTanggalMati.text.toString().trim()
            val jam_meninggal = binding.laporMatiTanggalMati.text.toString().trim()
            val nama_keluarga = binding.laporMatiNamaKeluarga.text.toString().trim()
            val kontak_keluarga = binding.laporMatiKontakKeluarga.text.toString().trim()

            when {
                TextUtils.isEmpty(nik) -> binding.laporMatiNik.error = InputUtils.FIELD_REQUIRED
                else -> {
                    laporkan(nik, nama, tempat_lahir, tanggal_lahir, tempat_meninggal, tanggal_meninggal, jam_meninggal, nama_keluarga, kontak_keluarga, "", this)
                }
            }
        })

    }


    interface ApiInterface {
        @Headers("Accept: application/json")
        @POST("Pelaporan")
        @FormUrlEncoded
        fun lapor_mati(
            @Field("request") request: String,
            @Field("nik") nik: String,
            @Field("nama_lengkap") nama_lengkap: String,
            @Field("tempat_lahir") tempat_lahir: String,
            @Field("tanggal_lahir") tanggal_lahir: String,
            @Field("tempat_meninggal") tempat_meninggal: String,
            @Field("tanggal_meninggal") tanggal_meninggal: String,
            @Field("jam_meninggal") jam_meninggal: String,
            @Field("nama_keluarga") nama_keluarga: String,
            @Field("no_handphone_keluarga") kontak_keluarga: String,
            @Field("kecamatan") kecamatan: String,
            @Field("kelurahan") kelurahan: String,
            @Field("jenis") jenis: String
        ): Call<LaporMati>
    }





    class LaporMati {
        @SerializedName("response_result")
        @Expose
        private val response_result: Int = 0

        @SerializedName("response_message")
        @Expose
        private val response_message: String = ""

        fun getResponseResult(): Int {
            return response_result
        }

        fun getResponseMessage(): String {
            return response_message
        }
    }





    private fun laporkan(
        nik: String,
        nama: String,
        tempat_lahir: String,
        tanggal_lahir: String,
        tempat_meninggal: String,
        tanggal_meninggal: String,
        jam_meninggal: String,
        nama_keluarga: String,
        kontak_keluarga: String,
        token: String?,
        context: Context) {
        var retIn: ApiInterface = RetroInstance.getRetrofitInstance(configuration.serverAPI).create(
            ApiInterface::class.java
        )

        retIn.lapor_mati(
            "tambah_pelaporan",
            nik,
            nama,
            tempat_lahir,
            tanggal_lahir,
            tempat_meninggal,
            tanggal_meninggal,
            jam_meninggal,
            nama_keluarga,
            kontak_keluarga,
            "1",
            "1",
            "1"
        ).enqueue(object : Callback<LaporMati> {
            override fun onFailure(call: Call<LaporMati>, t: Throwable) {
                val message: String? = t.message
                Log.e("TANAKA", message.toString())
            }

            override fun onResponse(call: Call<LaporMati>, response: Response<LaporMati>) {
                if (response.code() == 200) {
                    var dataset: LaporMati? = response.body()
                    if(dataset?.getResponseResult()!! > 0) {
                        StyleableToast.makeText(context, dataset.getResponseMessage(), Toast.LENGTH_LONG, R.style.toast_success).show();
                    } else {
                        StyleableToast.makeText(context, dataset.getResponseMessage(), Toast.LENGTH_LONG, R.style.toast_warning).show();
                    }
                } else if (response.code() == 400) {
                    //
                } else {
                    //
                }
                call.cancel()
            }
        })
    }
}