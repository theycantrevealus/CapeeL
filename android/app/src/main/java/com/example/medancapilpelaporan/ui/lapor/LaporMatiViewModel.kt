package com.example.medancapilpelaporan.ui.lapor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medancapilpelaporan.data.source.PelaporanRepository
import com.example.medancapilpelaporan.data.source.remote.network.ApiResponse
import com.example.medancapilpelaporan.data.source.remote.response.LaporMatiResponse
import com.example.medancapilpelaporan.data.source.remote.response.ResponsePackage
import com.example.medancapilpelaporan.data.source.remote.response.detail.LaporanMatiDetail
import com.example.medancapilpelaporan.data.source.remote.response.detail.LaporanPindahDetail
import com.example.medancapilpelaporan.utils.general.EventLiveData
import com.example.medancapilpelaporan.utils.general.HandlerLiveData
import kotlinx.coroutines.launch
import java.io.IOException

class LaporMatiViewModel(private val pelaporanRepository: PelaporanRepository): ViewModel() {

    //private val _postResult = MutableLiveData<ApiResponse<ResponsePackage>>()
    //val postResult: LiveData<ApiResponse<ResponsePackage>> = _postResult

    fun kirimLaporan(laporMatiResponse: LaporMatiResponse) = viewModelScope.launch {
        try {
            val result = pelaporanRepository.postLaporMati(laporMatiResponse)
            if (result != null) {
                //_postResult.value = ApiResponse.Success(result.responsePackage)
                _dialogHandler.value = EventLiveData(HandlerLiveData(result.responsePackage.responseResult, result.responsePackage.responseMessage))
            }
        } catch (e: IOException) {
            //_postResult.value = ApiResponse.Error(e.message)
            _dialogHandler.value = EventLiveData(HandlerLiveData(0, "Gagal terhubung. Periksa kembali jaringan anda"))
        }
    }

    private val _dialogHandler = MutableLiveData<EventLiveData<HandlerLiveData>>()
    val dialogHandler: LiveData<EventLiveData<HandlerLiveData>> = _dialogHandler


    private val _detailLaporan = MutableLiveData<ApiResponse<ArrayList<LaporanMatiDetail>>>()
    val detailLaporan: LiveData<ApiResponse<ArrayList<LaporanMatiDetail>>> = _detailLaporan

    fun getDetail(id: Int) = viewModelScope.launch {
        _detailLaporan.postValue(ApiResponse.Loading())
        try {
            val history = pelaporanRepository.getDetailMati(id)
            if (history != null)
                _detailLaporan.postValue(ApiResponse.Success(history))
            else {
                val emptyArray = ApiResponse.Success(ArrayList<LaporanMatiDetail>())
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