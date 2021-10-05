package com.example.medancapilpelaporan.ui.lapor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medancapilpelaporan.data.source.PelaporanRepository
import com.example.medancapilpelaporan.data.source.remote.network.ApiResponse
import com.example.medancapilpelaporan.data.source.remote.response.LaporMatiResponse
import com.example.medancapilpelaporan.data.source.remote.response.LaporPindahResponse
import com.example.medancapilpelaporan.data.source.remote.response.ResponsePackage
import kotlinx.coroutines.launch
import java.io.IOException

class LaporPindahViewModel(private val pelaporanRepository: PelaporanRepository): ViewModel() {

    private val _postResult = MutableLiveData<ApiResponse<ResponsePackage>>()
    val postResult: LiveData<ApiResponse<ResponsePackage>> = _postResult

    fun kirimLaporan(laporPindahResponse: LaporPindahResponse) = viewModelScope.launch {
        _postResult.value = ApiResponse.Loading()
        try {
            val result = pelaporanRepository.postLaporPindah(laporPindahResponse)
            if (result != null) {
                _postResult.value = ApiResponse.Success(result.responsePackage)
            }
        } catch (e: IOException) {
            _postResult.value = ApiResponse.Error(e.message)
        }
    }


}