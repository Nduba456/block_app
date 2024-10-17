package com.myapplication.di

import android.app.Application
import androidx.room.Room
import com.myapplication.database.AppDao
import com.myapplication.database.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [AppModule::class])
class DbModule(myApplication: Application) {

    private val dataDatabase: Database = Room.databaseBuilder(myApplication, Database::class.java, "room_db")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
    @Singleton
    @Provides
    internal fun providesDatabase(): Database {
        return dataDatabase
    }

    @Singleton
    @Provides
    internal fun providesDao(dataDatabase: Database): AppDao {
        return dataDatabase.appDao()

    }



}