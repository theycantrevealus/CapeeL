package com.example.medancapilpelaporan.ui.lapor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.medancapilpelaporan.databinding.ActivityLaporMatiBinding
import com.example.medancapilpelaporan.databinding.ActivityLaporPindahBinding

class LaporPindahActivity:AppCompatActivity() {
    private lateinit var binding: ActivityLaporPindahBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLaporPindahBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}