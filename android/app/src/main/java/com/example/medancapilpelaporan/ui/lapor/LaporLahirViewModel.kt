package com.example.medancapilpelaporan.ui.lapor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medancapilpelaporan.data.source.PelaporanRepository
import com.example.medancapilpelaporan.data.source.remote.network.ApiResponse
import com.example.medancapilpelaporan.data.source.remote.response.LaporLahirResponse
import com.example.medancapilpelaporan.data.source.remote.response.detail.LaporanLahirDetail
import com.example.medancapilpelaporan.data.source.remote.response.detail.LaporanMatiDetail
import com.example.medancapilpelaporan.utils.general.EventLiveData
import com.example.medancapilpelaporan.utils.general.HandlerLiveData
import kotlinx.coroutines.launch
import java.io.IOException

class LaporLahirViewModel(private val pelaporanRepository: PelaporanRepository): ViewModel() {

    private val _dialogHandler = MutableLiveData<EventLiveData<HandlerLiveData>>()
    val dialogHandler: LiveData<EventLiveData<HandlerLiveData>> = _dialogHandler

    fun kirimLaporan(laporLahirResponse: LaporLahirResponse) = viewModelScope.launch {
        try {
            val result = pelaporanRepository.postLaporLahir(laporLahirResponse)
            if (result != null) {
                _dialogHandler.value = EventLiveData(HandlerLiveData(result.responsePackage.responseResult, result.responsePackage.responseMessage))
            }
        } catch (e: IOException) {
            _dialogHandler.value = EventLiveData(HandlerLiveData(0, e.toString()))
        }
    }

    fun hapusLaporan(id: Int) = viewModelScope.launch {
        try {
            val result = pelaporanRepository.hapusLaporan(id)
            if (result != null) {
                _dialogHandler.value = EventLiveData(HandlerLiveData(result.responsePackage.responseResult, result.responsePackage.responseMessage))
            }
        } catch (e: IOException) {
            _dialogHandler.value = EventLiveData(HandlerLiveData(0, e.toString()))
        }
    }

    private val _detailLaporan = MutableLiveData<ApiResponse<ArrayList<LaporanLahirDetail>>>()
    val detailLaporan: LiveData<ApiResponse<ArrayList<LaporanLahirDetail>>> = _detailLaporan

    fun getDetail(id: Int) = viewModelScope.launch {
        _detailLaporan.postValue(ApiResponse.Loading())
        try {
            val history = pelaporanRepository.getDetailLahir(id)
            if (history != null)
                _detailLaporan.postValue(ApiResponse.Success(history))
            else {
                val emptyArray = ApiResponse.Success(ArrayList<LaporanLahirDetail>())
                _detailLaporan.postValue(emptyArray)
            }
        } catch (e: IOException) {
            _detailLaporan.postValue(ApiResponse.Error(e.message))
        }
    }
}