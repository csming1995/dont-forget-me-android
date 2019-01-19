package com.csming.dontforgetme.di

import com.csming.dontforgetme.repository.BookRepository
import com.csming.dontforgetme.repository.LoginRepository
import com.csming.dontforgetme.repository.RegisterRepository
import com.csming.dontforgetme.repository.UserRepository
import com.csming.dontforgetme.repository.impl.BookRepositoryImpl
import com.csming.dontforgetme.repository.impl.LoginRepositoryImpl
import com.csming.dontforgetme.repository.impl.RegisterRepositoryImpl
import com.csming.dontforgetme.repository.impl.UserRepositoryImpl
import dagger.Binds
import dagger.Module

/**
 * @author Created by csming on 2019/1/19.
 */
@Module
internal abstract class RepositoryModule {

    @Binds
    internal abstract fun bindBookRepository(repository: BookRepositoryImpl): BookRepository

    @Binds
    internal abstract fun bindRegisterRepository(repository: RegisterRepositoryImpl): RegisterRepository

    @Binds
    internal abstract fun bindLoginRepository(repository: LoginRepositoryImpl): LoginRepository

    @Binds
    internal abstract fun bindUserRepository(repository: UserRepositoryImpl): UserRepository
}
