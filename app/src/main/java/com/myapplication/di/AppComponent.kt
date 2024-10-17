package com.myapplication.di

import android.app.Application
import com.myapplication.BlockApplication
import com.myapplication.MainActivity
import com.myapplication.view.EmployeeFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjection
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class, FragmentModule::class] )
interface AppComponent: AndroidInjector<BlockApplication> {


    fun inject(mainActivity: MainActivity)
    fun inject(employeeFragment: EmployeeFragment)



    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application) : Builder

        fun appModule(appModule: AppModule) : Builder

//        fun dbModule(dbModule: DbModule) : Builder

        fun build() : AppComponent
    }

}