package com.example.mysky.di

import android.app.Application
import android.content.Context
import com.example.mysky.Api.ApiHelper
import com.example.mysky.Api.MovieAPIService
import com.example.mysky.BuildConfig
import com.example.mysky.data.ApiHelperImpl
import com.example.mysky.repository.MoviesRepository
import com.example.mysky.uiMain.MovieViewModel
import com.example.mysky.utils.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    repoModule,
                    viewModelModule
                )
            )
        }
    }

}

val appModule = module {
    single { provideOkHttpClient() }
    single {
        provideRetrofit(
            get(),
            BuildConfig.BASE_URL
        )
    }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }
    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}

val viewModelModule = module {
    viewModel {
        MovieViewModel(get(), get())
    }
}

val repoModule = module {
    single {
        MoviesRepository(get())
    }
}


private fun provideNetworkHelper(context: Context) =
    NetworkHelper(context)

private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
    OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
} else OkHttpClient
    .Builder()
    .build()

private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    BASE_URL: String
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

private fun provideApiService(retrofit: Retrofit): MovieAPIService =
    retrofit.create(MovieAPIService::class.java)

