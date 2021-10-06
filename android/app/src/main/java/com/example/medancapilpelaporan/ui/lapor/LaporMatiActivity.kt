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
import com.example.medancapilpelaporan.data.source.remote.response.LaporMatiResponse
import com.example.medancapilpelaporan.databinding.ActivityLaporMatiBinding
import com.example.medancapilpelaporan.databinding.ProgressLayoutDarkBinding
import com.example.medancapilpelaporan.ui.ViewModelFactory
import com.example.medancapilpelaporan.utils.general.InputUtils
import com.example.medancapilpelaporan.utils.picker.DatePickerFragment
import com.example.medancapilpelaporan.utils.picker.TimePickerFragment
import java.text.SimpleDateFormat
import java.util.*

class LaporMatiActivity: AppCompatActivity(), DatePickerFragment.DialogDateListener, TimePickerFragment.DialogTimeListener {

    private lateinit var binding : ActivityLaporMatiBinding
    private lateinit var progressLayoutBinding: ProgressLayoutDarkBinding
    private lateinit var viewModel: LaporMatiViewModel

    var dateField = ""

    private var jenis: String? = "add"

    companion object {
        private const val DATE_PICKER_TAG = "DatePicker"
        private const val TIME_PICKER_ONCE_TAG = "TimePickerOnce"

        //view or add
        const val AKSI = "jenis"
        const val ID_LAPORAN = "id_laporan"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLaporMatiBinding.inflate(layoutInflater)
        progressLayoutBinding = binding.progressLayout

        setContentView(binding.root)

        viewModel = obtainViewModel(this@LaporMatiActivity)
        viewModel.dialogHandler.observe(this, {
            it.getContentIfNotHandled()?.let { handler ->
                showLoading(false)
                binding.btnLaporkanMati.isEnabled = true
                showAlertDialog(handler.responseResult, handler.responseMessage, this)
            }
        })

        jenis = intent.getStringExtra(AKSI)

        if (jenis == "add") {
            binding.btnLaporkanMati.setOnClickListener(View.OnClickListener {
                val nik = binding.laporMatiNik.text.toString().trim()
                val nama = binding.laporMatiNama.text.toString().trim()
                val tempat_lahir = binding.laporMatiTempatLahir.text.toString().trim()
                val tanggal_lahir = binding.laporMatiTanggalLahir.text.toString().trim()
                val tempat_meninggal = binding.laporMatiTempatMati.text.toString().trim()
                val tanggal_meninggal = binding.laporMatiTanggalMati.text.toString().trim()
                val jam_meninggal = binding.laporJamMati.text.toString().trim()
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

                        binding.btnLaporkanMati.isEnabled = false
                        showLoading(true)
                        viewModel.kirimLaporan(dataLaporan)
                    }
                }
            })

            binding.laporMatiTanggalLahir.setOnClickListener {
                dateField ="lahir"
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)
            }

            binding.laporMatiTanggalMati.setOnClickListener {
                dateField ="mati"
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.show(supportFragmentManager, DATE_PICKER_TAG)
            }

            binding.laporJamMati.setOnClickListener {
                val timePickerFragment = TimePickerFragment()
                timePickerFragment.show(supportFragmentManager, TIME_PICKER_ONCE_TAG)
            }
        } else {

            binding.btnLaporkanMati.visibility = View.GONE
            binding.btnHapusLaporan.visibility = View.VISIBLE

            val id = intent.getIntExtra(ID_LAPORAN, 0)
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
                                    laporMatiNik.setText(detailData.nik)
                                    laporMatiNama.setText(detailData.namaLengkap)
                                    laporMatiTempatLahir.setText(detailData.tempatLahir)
                                    laporMatiTanggalLahir.setText(detailData.tanggalLahir)
                                    laporMatiTempatMati.setText(detailData.tempatMeninggal)
                                    laporMatiTanggalMati.setText(detailData.tanggalMeninggal)
                                    laporJamMati.setText(detailData.jamMeninggal)
                                    laporMatiNamaKeluarga.setText(detailData.namaKeluarga)
                                    laporMatiKontakKeluarga.setText(detailData.noHpKeluarga)

                                    laporMatiNik.setInputType(InputType.TYPE_NULL)
                                    laporMatiNama.setInputType(InputType.TYPE_NULL)
                                    laporMatiTempatLahir.setInputType(InputType.TYPE_NULL)
                                    laporMatiTanggalLahir.setInputType(InputType.TYPE_NULL)
                                    laporMatiTempatMati.setInputType(InputType.TYPE_NULL)
                                    laporMatiTanggalMati.setInputType(InputType.TYPE_NULL)
                                    laporJamMati.setInputType(InputType.TYPE_NULL)
                                    laporMatiNamaKeluarga.setInputType(InputType.TYPE_NULL)
                                    laporMatiKontakKeluarga.setInputType(InputType.TYPE_NULL)
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

        // Set text dari textview once
        when(dateField) {
            "lahir" -> {
                binding.laporMatiTanggalLahir.setText(dateFormat.format(calendar.time).toString())
            }
            "mati" -> {
                binding.laporMatiTanggalMati.setText(dateFormat.format(calendar.time).toString())
            }
        }

    }

    override fun onDialogTimeSet(tag: String?, hourOfDay: Int, minute: Int) {

        // Siapkan time formatter-nya terlebih dahulu
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)

        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        binding.laporJamMati.setText(dateFormat.format(calendar.time).toString())
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

    private fun obtainViewModel(activity: AppCompatActivity): LaporMatiViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(LaporMatiViewModel::class.java)
    }

}