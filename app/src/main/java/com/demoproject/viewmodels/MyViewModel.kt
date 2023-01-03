package com.demoproject.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demoproject.model.PicSumResponse
import com.demoproject.repository.DataRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MyViewModel(private val dataRepository: DataRepository): ViewModel() {

    companion object {
        const val TAG = "MyViewModel"
    }

    val PicSumLiveData = MutableLiveData<PicSumResponse>()

    val handler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG, "exception MyViewModel: $exception ")
    }

    init { }

    fun getImageList(endUrl: String){
        val imageListJob = viewModelScope.launch(handler) {
            dataRepository.getImageList(endUrl)
                .catch {
                    Log.d(TAG, "getImageList: ERROR $it")
                }.collect{
                    PicSumLiveData.value = it
                    Log.d(TAG, "getImageList: Success: $it")
                }
        }
        imageListJob.invokeOnCompletion {
            if (it != null) {
                Log.d(TAG, "getImageList: ${it.message.toString()}")
            }
        }

    }

    

}