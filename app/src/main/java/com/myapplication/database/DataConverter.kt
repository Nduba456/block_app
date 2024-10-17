package com.myapplication.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.myapplication.model.Employee
import com.myapplication.model.EmployeeX

class DataConverter {


    @TypeConverter
    fun fromEmployeeList(value: List<EmployeeX>?): String? {
        return value?.let { Gson().toJson(value) }
    }

    @TypeConverter
    fun toEmployeeList(value: String?): List<EmployeeX>? {
        return value?.let {
            val listType = object : TypeToken<List<EmployeeX>>() {}.type
            Gson().fromJson(value, listType)
        }
    }
}