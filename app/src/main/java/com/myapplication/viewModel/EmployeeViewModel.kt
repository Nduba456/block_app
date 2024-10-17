package com.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapplication.model.Employee
import com.myapplication.service.repository.Repository
import com.myapplication.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmployeeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    private val _employeeData = MutableLiveData<Resource<Employee>>()
    val employeeData: LiveData<Resource<Employee>> get() = _employeeData

    fun getEmployee() {
        viewModelScope.launch {

            _employeeData.postValue(Resource.Loading)

//            assign response
            val response = repository.getEmployees()
            _employeeData.postValue(response)
        }
    }

}