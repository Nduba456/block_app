package com.myapplication.service

import com.myapplication.model.Employee
import com.myapplication.model.EmployeeX
import retrofit2.http.GET

interface Api {

    @GET("employees.json")
    suspend fun getEmployee(): Employee
}