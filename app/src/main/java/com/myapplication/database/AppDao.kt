package com.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.myapplication.model.Employee
import com.myapplication.model.EmployeeX


@Dao
interface AppDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmployee(employeeX: Employee)


    @Query("SELECT * FROM 'employee'")
    fun getAllEmployees() : LiveData<Employee>

}