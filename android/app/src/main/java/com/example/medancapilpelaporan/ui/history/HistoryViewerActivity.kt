package com.example.medancapilpelaporan.ui.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.medancapilpelaporan.R
import com.example.medancapilpelaporan.databinding.ActivityHistoryViewerBinding

class HistoryViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryViewerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, HistoryFragment())
                .commitNow()
        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}