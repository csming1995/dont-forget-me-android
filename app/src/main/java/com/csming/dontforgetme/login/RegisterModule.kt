package com.csming.dontforgetme.login

import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.common.di.ViewModelKey
import com.csming.dontforgetme.login.repository.RegisterRepository
import com.csming.dontforgetme.login.repository.impl.RegisterRepositoryImpl
import com.csming.dontforgetme.login.viewmodel.RegisterViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
internal abstract class RegisterModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    internal abstract fun bindRegisterViewModel(viewModel: RegisterViewModel): ViewModel

    @Binds
    internal abstract fun bindRegisterRepository(repository: RegisterRepositoryImpl): RegisterRepository
}
