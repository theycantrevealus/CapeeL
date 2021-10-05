package com.example.medancapilpelaporan.ui.lapor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medancapilpelaporan.data.source.PelaporanRepository
import com.example.medancapilpelaporan.data.source.remote.network.ApiResponse
import com.example.medancapilpelaporan.data.source.remote.response.LaporLahirResponse
import com.example.medancapilpelaporan.data.source.remote.response.ResponsePackage
import kotlinx.coroutines.launch
import java.io.IOException

class LaporLahirViewModel(private val pelaporanRepository: PelaporanRepository): ViewModel() {

    private val _postResult = MutableLiveData<ApiResponse<ResponsePackage>>()
    val postResult: LiveData<ApiResponse<ResponsePackage>> = _postResult

    fun kirimLaporan(laporLahirResponse: LaporLahirResponse) = viewModelScope.launch {
        _postResult.value = ApiResponse.Loading()
        try {
            val result = pelaporanRepository.postLaporLahir(laporLahirResponse)
            if (result != null) {
                _postResult.value = ApiResponse.Success(result.responsePackage)
            }
        } catch (e: IOException) {
            _postResult.value = ApiResponse.Error(e.message)
        }
    }


}