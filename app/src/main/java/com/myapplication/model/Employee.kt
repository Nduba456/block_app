package com.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.myapplication.database.DataConverter


@Entity(tableName = "employee")
@TypeConverters(DataConverter::class)
data class Employee(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val employees: List<EmployeeX>
)