package com.csming.dontforgetme.login

import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.common.di.ViewModelKey
import com.csming.dontforgetme.login.viewmodel.LoginViewModel
import com.csming.dontforgetme.login.viewmodel.RegisterViewModel
import com.csming.dontforgetme.main.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class RegisterModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    internal abstract fun bindRegisterViewModel(viewModel: RegisterViewModel): ViewModel
}
