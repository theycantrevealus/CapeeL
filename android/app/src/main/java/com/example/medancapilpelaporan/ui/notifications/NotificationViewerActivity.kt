package com.example.medancapilpelaporan.ui.notifications

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.medancapilpelaporan.databinding.ActivityNotificationViewerBinding

//notification is profile setting
class NotificationViewerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNotificationViewerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNotificationViewerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, NotificationsFragment())
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