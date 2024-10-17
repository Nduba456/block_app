package com.myapplication.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.myapplication.model.Employee



@TypeConverters(DataConverter::class)
@androidx.room.Database(entities = [Employee:: class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase(){


    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var instance: Database? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it}
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                Database::class.java,
                "room_db"
            )
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

}