package com.myapplication.di

import android.app.Application
import android.content.Context
import com.myapplication.service.Api
import com.myapplication.service.RetrofitInstance
import com.myapplication.service.repository.BaseRepository
import com.myapplication.service.repository.BaseRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val context: Context) {


    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun provideApiInstance(retrofitInstance: RetrofitInstance) : Api {
        return retrofitInstance.buildApi(Api::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(): BaseRepository{
        return BaseRepositoryImpl()
    }

}