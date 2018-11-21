package com.csming.dontforgetme.main

import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.common.di.FragmentScoped
import com.csming.dontforgetme.common.di.ViewModelKey
import com.csming.dontforgetme.main.fragment.BooksFragment
import com.csming.dontforgetme.main.repository.BookRepository
import com.csming.dontforgetme.main.repository.impl.BookRepositoryImpl
import com.csming.dontforgetme.main.viewmodel.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    internal abstract fun bindBookRepository(repository: BookRepositoryImpl): BookRepository

    @FragmentScoped
    @ContributesAndroidInjector(modules = [])
    internal abstract fun contributeInfoFragment(): BooksFragment
}
