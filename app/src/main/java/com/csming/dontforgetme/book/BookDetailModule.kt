package com.csming.dontforgetme.book

import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.book.viewmodel.BookDetailViewModel
import com.csming.dontforgetme.common.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class BookDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(BookDetailViewModel::class)
    internal abstract fun bindBookDetailViewModel(viewModel: BookDetailViewModel): ViewModel
}
