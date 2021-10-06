package com.example.medancapilpelaporan.ui.lapor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.medancapilpelaporan.data.source.remote.network.ApiResponse
import com.example.medancapilpelaporan.data.source.remote.response.LaporLahirResponse
import com.example.medancapilpelaporan.databinding.ActivityLaporLahirBinding
import com.example.medancapilpelaporan.databinding.ProgressLayoutDarkBinding
import com.example.medancapilpelaporan.ui.ViewModelFactory
import com.example.medancapilpelaporan.utils.general.InputUtils
import com.example.medancapilpelaporan.utils.picker.DatePickerFragment
import java.text.SimpleDateFormat
import java.util.*

class LaporLahirActivity: AppCompatActivity(), DatePickerFragment.DialogDateListener {

    private lateinit var binding: ActivityLaporLahirBinding
    private lateinit var progressLayoutBinding: ProgressLayoutDarkBinding
    private lateinit var viewModel: LaporLahirViewModel

    private var jenis: String? = "add"

    companion object {
        private const val DATE_PICKER_TAG = "DatePicker"

        //view or add
        const val AKSI = "jenis"
        const val ID_LAPORAN = "id_laporan"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLaporLahirBinding.inflate(layoutInflater)
        progressLayoutBinding = binding.progressLayout
        setContentView(binding.root)

        viewModel = obtainViewModel(this@LaporLahirActivity)

        viewModel.dialogHandler.observe(this, {
            it.getContentIfNotHandled()?.let { handler ->
                showLoading(false)
                binding.btnLaporkanLahir.isEnabled = true
                binding.btnHapusLaporan.isEnabled = true
                showAlertDialog(handler.responseResult, handler.responseMessage, this)
            }
        })

        jenis = intent.getStringExtra(AKSI)

        if (jenis == "add") {
            binding.btnLaporkanLahir.setOnClickListener {
                val nama = binding.laporLahirNamaAnak.text.toString().trim()
                val tempat_lahir = binding.laporLahirTempatLahir.text.toString().trim()
                val tanggal_lahir = binding.laporLahirTanggalLahir.text.toString().trim()
                val nik_ortu = binding.laporLahirNikOrtu.text.toString().trim()
                val nama_ortu = binding.laporLahirNamaOrtu.text.toString().trim()
                val alamat = binding.laporLahirAlamat.text.toString().trim()

                when {
                    TextUtils.isEmpty(nik_ortu) -> binding.laporLahirNikOrtu.error = InputUtils.FIELD_REQUIRED
                    else -> {
                        val dataLaporan = LaporLahirResponse(
                            nik_ortu,
                            nama_ortu,
                            tempat_lahir,
                            tanggal_lahir,
                            nama,
                            alamat
                        )

                        binding.btnLaporkanLahir.isEnabled = false
                        showLoading(true)
                        viewModel.kirimLaporan(dataLaporan)
                    }
                }
            }

            binding.laporLahirTanggalLahir.setOnClickListener {
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)
            }

        } else {
            binding.btnLaporkanLahir.visibility = View.GONE
            binding.btnHapusLaporan.visibility = View.VISIBLE

            val id = intent.getIntExtra(LaporPindahActivity.ID_LAPORAN, 0)
            binding.btnHapusLaporan.setOnClickListener {
                binding.btnHapusLaporan.isEnabled = false
                showConfirmDelete(id,this)
            }

            showLoading(true)
            viewModel.getDetail(id)
            viewModel.detailLaporan.observe(this, { response ->
                when(response) {
                    is ApiResponse.Success -> {
                        response.data?.let {
                            if (it.size > 0) {
                                val detailData = it[0]

                                with(binding) {
                                    laporLahirNamaAnak.setText(detailData.namaAnak)
                                    laporLahirTempatLahir.setText(detailData.tempatLahir)
                                    laporLahirTanggalLahir.setText(detailData.tanggalLahir)
                                    laporLahirNikOrtu.setText(detailData.nikOrtu)
                                    laporLahirNamaOrtu.setText(detailData.namaOrtu)
                                    laporLahirAlamat.setText(detailData.alamat)

                                    laporLahirNamaAnak.setInputType(InputType.TYPE_NULL)
                                    laporLahirTempatLahir.setInputType(InputType.TYPE_NULL)
                                    laporLahirTanggalLahir.setInputType(InputType.TYPE_NULL)
                                    laporLahirNikOrtu.setInputType(InputType.TYPE_NULL)
                                    laporLahirNamaOrtu.setInputType(InputType.TYPE_NULL)
                                    laporLahirAlamat.setInputType(InputType.TYPE_NULL)
                                }
                            } else {
                                Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_SHORT).show()
                            }
                        }
                        showLoading(false)
                    }
                    is ApiResponse.Error -> {
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        showLoading(false)
                    }
                    is ApiResponse.Loading -> {
                        showLoading(true)
                    }
                }
            })
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        // Siapkan date formatter-nya terlebih dahulu
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        binding.laporLahirTanggalLahir.setText(dateFormat.format(calendar.time).toString())
    }

    private fun showLoading(state: Boolean) {
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
                    if (jenis == "view") {
                        val mIntent = Intent()
                        mIntent.putExtra("auto_refresh", true)
                        setResult(RESULT_OK, mIntent)
                    }
                    finish()
                }
                dialog.cancel()
            }

        val alertDialog = alertDialogBuilder.create()

        showLoading(false)
        alertDialog.show()
    }

    private fun showConfirmDelete(idLaporan: Int, context: Context) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        val dialogTitle = "Hapus"

        alertDialogBuilder.setTitle(dialogTitle)
        alertDialogBuilder
            .setMessage("Yakin hapus laporan?")
            .setCancelable(true)
            .setNegativeButton("Batal") { dialog, _ ->
                binding.btnHapusLaporan.isEnabled = true
                dialog.cancel()
            }
            .setPositiveButton("Hapus") { dialog, id ->
                showLoading(true)
                viewModel.hapusLaporan(idLaporan)
                dialog.cancel()
            }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun obtainViewModel(activity: AppCompatActivity): LaporLahirViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(LaporLahirViewModel::class.java)
    }
}