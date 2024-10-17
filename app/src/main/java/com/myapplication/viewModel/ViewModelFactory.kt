package com.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.myapplication.service.repository.BaseRepository
import com.myapplication.service.repository.Repository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val repository: BaseRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(EmployeeViewModel::class.java) -> EmployeeViewModel(repository as Repository) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


}