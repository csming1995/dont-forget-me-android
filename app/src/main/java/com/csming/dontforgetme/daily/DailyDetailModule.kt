package com.csming.dontforgetme.daily

import androidx.lifecycle.ViewModel
import com.csming.dontforgetme.common.di.ViewModelKey
import com.csming.dontforgetme.daily.viewmodel.DailyDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class DailyDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(DailyDetailViewModel::class)
    internal abstract fun bindDailyDetailViewModel(viewModel: DailyDetailViewModel): ViewModel
}
