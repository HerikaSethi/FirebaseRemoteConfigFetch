package com.demoproject.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demoproject.repository.DataRepository

class ViewModelFactory(private val dataRepository: DataRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        return MyViewModel(dataRepository) as T
    }
}