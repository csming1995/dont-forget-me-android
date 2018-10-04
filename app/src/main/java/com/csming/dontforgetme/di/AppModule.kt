package com.csming.dontforgetme.di

import android.content.ClipboardManager
import android.content.Context
import android.net.wifi.WifiManager
import com.csming.dontforgetme.MainApplication
import com.csming.dontforgetme.common.config.ApiConfig
import com.csming.dontforgetme.common.config.BASE_URL
import com.csming.dontforgetme.login.api.LoginApi
import com.csming.dontforgetme.login.api.RegisterApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
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
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    fun providerRegisterApi(retrofit: Retrofit): RegisterApi {
        return retrofit.create(RegisterApi::class.java)
    }

    @Provides
    fun providerLoginApi(retrofit: Retrofit): LoginApi {
        return retrofit.create(LoginApi::class.java)
    }
}
