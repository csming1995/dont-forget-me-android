package com.csming.dontforgetme.common.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: DontForgetMeViewModelFactory):
        ViewModelProvider.Factory
}
