package com.example.mediaapp.apirequests

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory {
    private val authInterceptor =  Interceptor {chain ->
        val newUrl = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", "4d8b26c7e474409c5926dca8433a8262")
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val tmdbClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .build()

    fun retrofit() : Retrofit = Retrofit.Builder()
        .client(tmdbClient)
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val tmdbApi : TmdbApi = retrofit().create(TmdbApi::class.java)
}