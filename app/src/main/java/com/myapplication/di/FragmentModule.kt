package com.myapplication.di

import com.myapplication.view.EmployeeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginInjector() : EmployeeFragment

}