package com.example.mysky.Api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieAPI {
    private const val BASE_URL = "https://sky-exercise.herokuapp.com/"
//    private var retrofit: Retrofit? = null
//    private fun buildClient(): OkHttpClient {
//        return OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//            .build()
//    }
//
//    @JvmStatic
//    val client: Retrofit?
//        get() {
//            if (retrofit == null) {
//                retrofit = Retrofit.Builder()
//                    .client(buildClient())
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .baseUrl(BASE_URL)
//                    .build()
//            }
//            return retrofit
//        }


    private var servicesApiInterface:MovieAPIService?=null

    fun build():MovieAPIService?{
        var builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

        var httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor())

        var retrofit: Retrofit = builder.client(httpClient.build()).build()
        servicesApiInterface = retrofit.create(
            MovieAPIService::class.java)

        return servicesApiInterface as MovieAPIService
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level=HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

}