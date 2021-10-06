package com.example.medancapilpelaporan.ui.lapor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medancapilpelaporan.data.source.PelaporanRepository
import com.example.medancapilpelaporan.data.source.remote.network.ApiResponse
import com.example.medancapilpelaporan.data.source.remote.response.HistoryPelaporan
import com.example.medancapilpelaporan.data.source.remote.response.LaporMatiResponse
import com.example.medancapilpelaporan.data.source.remote.response.LaporPindahResponse
import com.example.medancapilpelaporan.data.source.remote.response.ResponsePackage
import com.example.medancapilpelaporan.data.source.remote.response.detail.LaporanPindahDetail
import com.example.medancapilpelaporan.utils.general.EventLiveData
import com.example.medancapilpelaporan.utils.general.HandlerLiveData
import kotlinx.coroutines.launch
import java.io.IOException

class LaporPindahViewModel(private val pelaporanRepository: PelaporanRepository): ViewModel() {

    private val _dialogHandler = MutableLiveData<EventLiveData<HandlerLiveData>>()
    val dialogHandler: LiveData<EventLiveData<HandlerLiveData>> = _dialogHandler

    fun kirimLaporan(laporPindahResponse: LaporPindahResponse) = viewModelScope.launch {
        try {
            val result = pelaporanRepository.postLaporPindah(laporPindahResponse)
            if (result != null) {
                _dialogHandler.value = EventLiveData(HandlerLiveData(result.responsePackage.responseResult, result.responsePackage.responseMessage))
            }
        } catch (e: IOException) {
            _dialogHandler.value = EventLiveData(HandlerLiveData(0, e.toString()))
        }
    }


    private val _detailLaporan = MutableLiveData<ApiResponse<ArrayList<LaporanPindahDetail>>>()
    val detailLaporan: LiveData<ApiResponse<ArrayList<LaporanPindahDetail>>> = _detailLaporan

    fun getDetail(id: Int) = viewModelScope.launch {
        _detailLaporan.postValue(ApiResponse.Loading())
        try {
            val history = pelaporanRepository.getDetailPindah(id)
            if (history != null)
                _detailLaporan.postValue(ApiResponse.Success(history))
            else {
                val emptyArray = ApiResponse.Success(ArrayList<LaporanPindahDetail>())
                _detailLaporan.postValue(emptyArray)
            }
        } catch (e: IOException) {
            _detailLaporan.postValue(ApiResponse.Error(e.message))
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

}