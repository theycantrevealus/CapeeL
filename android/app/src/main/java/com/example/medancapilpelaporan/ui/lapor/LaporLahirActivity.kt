package com.example.medancapilpelaporan.ui.lapor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.medancapilpelaporan.databinding.ActivityLaporLahirBinding

class LaporLahirActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLaporLahirBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLaporLahirBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}