package com.example.medancapilpelaporan.ui.lapor

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.util.Log.d
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.medancapilpelaporan.R
import com.example.medancapilpelaporan.data.source.remote.network.ApiResponse
import com.example.medancapilpelaporan.data.source.remote.response.LaporPindahResponse
import com.example.medancapilpelaporan.databinding.ActivityLaporPindahBinding
import com.example.medancapilpelaporan.databinding.ProgressLayoutDarkBinding
import com.example.medancapilpelaporan.ui.ViewModelFactory
import com.example.medancapilpelaporan.utils.general.InputUtils
import com.example.medancapilpelaporan.utils.picker.DatePickerFragment
import java.text.SimpleDateFormat
import java.util.*

class LaporPindahActivity:AppCompatActivity() {

    private lateinit var binding: ActivityLaporPindahBinding
    private lateinit var progressLayoutBinding: ProgressLayoutDarkBinding
    private lateinit var viewModel: LaporPindahViewModel

    private var jenis: String? = "add"

    companion object {
        //view or add
        const val AKSI = "jenis"
        const val ID_LAPORAN = "id_laporan"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLaporPindahBinding.inflate(layoutInflater)
        progressLayoutBinding = binding.progressLayout
        setContentView(binding.root)

        viewModel = obtainViewModel(this@LaporPindahActivity)
        viewModel.dialogHandler.observe(this, {
            it.getContentIfNotHandled()?.let { handler ->
                showLoading(false)
                binding.btnLaporkanPindah.isEnabled = true
                showAlertDialog(handler.responseResult, handler.responseMessage, this)
            }
        })

        val spinnerArrayAdapter = ArrayAdapter.createFromResource(
            this,
            R.array.jenis_pindah,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerJenisPindah.adapter = adapter
        }


        jenis = intent.getStringExtra(AKSI)

        if (jenis == "add") {

            binding.btnLaporkanPindah.setOnClickListener {
                val nik = binding.nik.text.toString().trim()
                val nama = binding.nama.text.toString().trim()
                val alamat = binding.alamat.text.toString().trim()
                val statusKeluarga = binding.statusKeluarga.text.toString().trim()

                val itemPosition = binding.spinnerJenisPindah.selectedItemPosition
                val jenisPindah = binding.spinnerJenisPindah.getItemAtPosition(itemPosition).toString()

                when {
                    TextUtils.isEmpty(nik) -> binding.nik.error = InputUtils.FIELD_REQUIRED
                    else -> {
                        val dataLaporan = LaporPindahResponse(
                            nik,
                            nama,
                            alamat,
                            jenisPindah,
                            statusKeluarga
                        )

                        binding.btnLaporkanPindah.isEnabled = false
                        showLoading(true)
                        viewModel.kirimLaporan(dataLaporan)
                    }
                }
            }
        } else {
            binding.btnLaporkanPindah.visibility = View.GONE
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
                                    nik.setText(detailData.nik)
                                    nama.setText(detailData.nama)
                                    alamat.setText(detailData.alamat)
                                    statusKeluarga.setText(detailData.statusKeluarga)

                                    binding.spinnerJenisPindah.setSelection(
                                        spinnerArrayAdapter.getPosition(detailData.jenisPindah)
                                    )

                                    nik.inputType = InputType.TYPE_NULL
                                    nama.inputType = InputType.TYPE_NULL
                                    alamat.inputType = InputType.TYPE_NULL
                                    statusKeluarga.inputType = InputType.TYPE_NULL
                                    spinnerJenisPindah.isEnabled = false
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


    private fun obtainViewModel(activity: AppCompatActivity): LaporPindahViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(LaporPindahViewModel::class.java)
    }

}