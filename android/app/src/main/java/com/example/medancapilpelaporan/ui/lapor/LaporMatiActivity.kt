package com.example.medancapilpelaporan.ui.lapor

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.medancapilpelaporan.data.source.remote.network.ApiResponse
import com.example.medancapilpelaporan.data.source.remote.response.LaporMatiResponse
import com.example.medancapilpelaporan.databinding.ActivityLaporMatiBinding
import com.example.medancapilpelaporan.databinding.ProgressLayoutDarkBinding
import com.example.medancapilpelaporan.ui.ViewModelFactory
import com.example.medancapilpelaporan.utils.general.InputUtils

class LaporMatiActivity: AppCompatActivity() {

    private lateinit var binding : ActivityLaporMatiBinding
    private lateinit var progressLayoutBinding: ProgressLayoutDarkBinding
    private lateinit var viewModel: LaporMatiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLaporMatiBinding.inflate(layoutInflater)
        progressLayoutBinding = binding.progressLayout

        setContentView(binding.root)

        viewModel = obtainViewModel(this@LaporMatiActivity)
        viewModel.postResult.observe(this, { response ->

            when(response) {
                is ApiResponse.Success -> {
                    response.data?.responseMessage.let {
                        if (response.data != null) {
                            showAlertDialog(response.data.responseResult, response.data.responseMessage, this)
                        }
                    }
                }
                is ApiResponse.Error -> {
                    response.message.let {
                        showAlertDialog(0, "Terjadi kesalahan, mohon hubungi sistem administrator", this)
                    }
                }
                is ApiResponse.Loading -> {
                    showLoading(true)
                }
            }

        })

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
                    val dataLaporan = LaporMatiResponse(
                        nik,
                        nama,
                        tempat_lahir,
                        tanggal_lahir,
                        tempat_meninggal,
                        tanggal_meninggal,
                        jam_meninggal,
                        nama_keluarga,
                        kontak_keluarga
                    )

                    viewModel.kirimLaporan(dataLaporan)
                }
            }
        })
    }

    fun showLoading(state: Boolean) {
        if(state) {
            progressLayoutBinding.root.visibility = View.VISIBLE
        } else {
            progressLayoutBinding.root.visibility = View.GONE
        }
    }

    private fun showAlertDialog(result: Int, msg: String, context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        val dialogTitle = "Informasi"

        alertDialogBuilder.setTitle(dialogTitle)
        alertDialogBuilder
            .setMessage(msg)
            .setCancelable(false)
            .setPositiveButton("Oke") { dialog, id ->
                if (result == 1) {
                    finish()
                }
                dialog.cancel()
            }

        val alertDialog = alertDialogBuilder.create()

        showLoading(false)
        alertDialog.show()
    }

    private fun obtainViewModel(activity: AppCompatActivity): LaporMatiViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(LaporMatiViewModel::class.java)
    }

}