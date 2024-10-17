package com.myapplication.service.repository

import android.util.Log
import com.myapplication.database.AppDao
import com.myapplication.model.Employee
import com.myapplication.service.Api
import com.myapplication.utils.NetworkCheck
import com.myapplication.utils.Resource
import android.content.Context
import javax.inject.Inject

class Repository @Inject constructor(private val api: Api,
                                     private val appDao: AppDao, private val context: Context,) : BaseRepository() {


//    Get all employees from API save to My room Database
    suspend fun getEmployees() : Resource<Employee> = safeApiCall{
        Log.d("Repository", "Service Call")

        if (NetworkCheck.isNetworkAvailable(context)) {
            val response = api.getEmployee()
            if (response.employees.isNotEmpty()) {
                appDao.insertEmployee(response)
            }
            response
        } else {
            if (!NetworkCheck.isNetworkAvailable(context)) {
                Resource.Failure(true, null, null, "Secure Internet Connection")

                appDao.getAllEmployees()
            }

            error("Failed")
        }

    }
}