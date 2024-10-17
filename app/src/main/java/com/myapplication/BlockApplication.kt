package com.myapplication

import android.app.Application
import com.myapplication.di.AppModule
import com.myapplication.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject
import javax.inject.Qualifier

class BlockApplication: Application(), HasAndroidInjector {


    @Inject
    lateinit var myInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .application(Application())
            .build()
            .inject(this)
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ApplicationContext



    override fun androidInjector(): AndroidInjector<Any> {
        return myInjector
    }


}