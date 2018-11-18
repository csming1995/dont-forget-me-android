package com.csming.dontforgetme.timeline

import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.common.di.ViewModelKey
import com.csming.dontforgetme.timeline.viewmodel.TimelineViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class TimelineModule {

    @Binds
    @IntoMap
    @ViewModelKey(TimelineViewModel::class)
    internal abstract fun bindTimelineViewModel(viewModel: TimelineViewModel): ViewModel

//    @Binds
//    internal abstract fun bindBookRepository(repository: BookRepositoryImpl): BookRepository
}
