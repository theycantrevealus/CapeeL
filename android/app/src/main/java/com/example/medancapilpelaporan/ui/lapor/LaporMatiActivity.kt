package com.example.medancapilpelaporan.ui.lapor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.medancapilpelaporan.databinding.ActivityLaporMatiBinding

class LaporMatiActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLaporMatiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLaporMatiBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}