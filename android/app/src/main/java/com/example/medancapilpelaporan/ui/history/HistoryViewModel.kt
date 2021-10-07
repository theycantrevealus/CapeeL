package com.example.medancapilpelaporan.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medancapilpelaporan.data.source.PelaporanRepository
import com.example.medancapilpelaporan.data.source.remote.network.ApiResponse
import com.example.medancapilpelaporan.data.source.remote.response.HistoryPelaporan
import kotlinx.coroutines.launch
import java.io.IOException

class HistoryViewModel(private val pelaporanRepository: PelaporanRepository): ViewModel() {

    private val _listPelaporan = MutableLiveData<ApiResponse<ArrayList<HistoryPelaporan>>>()
    val listPelaporan: LiveData<ApiResponse<ArrayList<HistoryPelaporan>>> = _listPelaporan

    fun getHistory() = viewModelScope.launch {
        _listPelaporan.postValue(ApiResponse.Loading())
        try {
            val history = pelaporanRepository.getHistory()
            if (history != null)
                _listPelaporan.postValue(ApiResponse.Success(history))
            else {
                val emptyArray = ApiResponse.Success(ArrayList<HistoryPelaporan>())
                _listPelaporan.postValue(emptyArray)
            }
        } catch (e: IOException) {
            _listPelaporan.postValue(ApiResponse.Error(e.message))
        }
    }

    fun resetHistory() {
        _listPelaporan.postValue(ApiResponse.Success(arrayListOf()))
    }



}