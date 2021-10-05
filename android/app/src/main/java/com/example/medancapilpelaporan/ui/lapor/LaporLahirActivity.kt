package com.example.medancapilpelaporan.ui.lapor

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.medancapilpelaporan.databinding.ActivityLaporLahirBinding

class LaporLahirActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLaporLahirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLaporLahirBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.laporLahirTempatLahir.setOnClickListener{
            //
        }

        binding.btnLaporkanLahir.setOnClickListener {
            val nama = binding.laporLahirNamaAnak.text.toString().trim()
            val tempat_lahir = binding.laporLahirTempatLahir.text.toString().trim()
            val tanggal_lahir = binding.laporLahirTanggalLahir.text.toString().trim()
            val nik_ortu = binding.laporLahirNikOrtu.text.toString().trim()
            val nama_ortu = binding.laporLahirNamaOrtu.text.toString().trim()
            val alamat = binding.laporLahirAlamat.text.toString().trim()
        }

    }
}