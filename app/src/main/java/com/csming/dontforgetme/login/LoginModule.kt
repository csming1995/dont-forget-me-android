package com.csming.dontforgetme.login

import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.common.di.ViewModelKey
import com.csming.dontforgetme.login.viewmodel.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}
