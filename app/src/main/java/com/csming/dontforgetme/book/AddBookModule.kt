package com.csming.dontforgetme.book

import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.book.viewmodel.AddBookViewModel
import com.csming.dontforgetme.book.viewmodel.BookDetailViewModel
import com.csming.dontforgetme.common.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class AddBookModule {

    @Binds
    @IntoMap
    @ViewModelKey(AddBookViewModel::class)
    internal abstract fun bindAddBookViewModel(viewModel: AddBookViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookDetailViewModel::class)
    internal abstract fun bindBookDetailViewModel(viewModel: BookDetailViewModel): ViewModel
}
