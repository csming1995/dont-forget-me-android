package com.csming.dontforgetme.di

import android.content.ClipboardManager
import android.content.Context
import android.net.wifi.WifiManager
import com.csming.dontforgetme.MainApplication
import com.csming.dontforgetme.api.AccountApi
import com.csming.dontforgetme.api.BookApi
import com.csming.dontforgetme.api.DailyApi
import com.csming.dontforgetme.common.config.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class AppModule {

    @Provides
    fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }

    @Provides
    fun providesWifiManager(context: Context): WifiManager =
        context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

    @Provides
    fun providesClipboardManager(context: Context): ClipboardManager =
        context.applicationContext.getSystemService(Context.CLIPBOARD_SERVICE)
            as ClipboardManager

    @Provides
    fun provideBaseRetrofit(): Retrofit {
        val client = OkHttpClient()
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    fun providerAccountApi(retrofit: Retrofit): AccountApi {
        return retrofit.create(AccountApi::class.java)
    }

    @Provides
    fun providerBookApi(retrofit: Retrofit): BookApi {
        return retrofit.create(BookApi::class.java)
    }

    @Provides
    fun providerRecordingApi(retrofit: Retrofit): DailyApi {
        return retrofit.create(DailyApi::class.java)
    }
}
