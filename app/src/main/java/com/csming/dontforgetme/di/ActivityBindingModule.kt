/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.csming.dontforgetme.di

import com.csming.dontforgetme.LaucherActivity
import com.csming.dontforgetme.common.di.ActivityScoped
import com.csming.dontforgetme.login.activity.LoginActivity
import com.csming.dontforgetme.login.LoginModule
import com.csming.dontforgetme.login.RegisterModule
import com.csming.dontforgetme.login.activity.RegisterActivity
import com.csming.dontforgetme.main.MainActivity
import com.csming.dontforgetme.main.MainModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector()
    internal abstract fun launcherActivity(): LaucherActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [LoginModule::class])
    internal abstract fun loginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [RegisterModule::class])
    internal abstract fun registerActivity(): RegisterActivity

    @ActivityScoped
    @ContributesAndroidInjector(modules = [MainModule::class])
    internal abstract fun mainActivity(): MainActivity
}
