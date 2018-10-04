package com.csming.dontforgetme.login

import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.common.di.ViewModelKey
import com.csming.dontforgetme.login.repository.LoginRepository
import com.csming.dontforgetme.login.repository.RegisterRepository
import com.csming.dontforgetme.login.repository.impl.LoginRepositoryImpl
import com.csming.dontforgetme.login.repository.impl.RegisterRepositoryImpl
import com.csming.dontforgetme.login.viewmodel.LoginViewModel
import com.csming.dontforgetme.main.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class LoginModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    internal abstract fun bindLoginRepository(repository: LoginRepositoryImpl): LoginRepository
}
