package com.example.medancapilpelaporan.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.medancapilpelaporan.data.source.PelaporanRepository
import com.example.medancapilpelaporan.di.Injection
import com.example.medancapilpelaporan.ui.history.HistoryViewModel
import com.example.medancapilpelaporan.ui.lapor.LaporLahirViewModel
import com.example.medancapilpelaporan.ui.lapor.LaporMatiViewModel
import com.example.medancapilpelaporan.ui.lapor.LaporPindahViewModel

class ViewModelFactory private constructor(private val pelaporanRepository: PelaporanRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(LaporMatiViewModel::class.java) -> {
                return LaporMatiViewModel(pelaporanRepository) as T
            }
            modelClass.isAssignableFrom(LaporLahirViewModel::class.java) -> {
                return LaporLahirViewModel(pelaporanRepository) as T
            }
            modelClass.isAssignableFrom(LaporPindahViewModel::class.java) -> {
                return LaporPindahViewModel(pelaporanRepository) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                return HistoryViewModel(pelaporanRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel classs: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(application: Application): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(application)).apply {
                instance = this
            }
        }
    }


}