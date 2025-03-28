package com.example.api

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context): OkHttpClient {
        val chucker = ChuckerInterceptor.Builder(context).build()
        Log.d("NetworkModule", "Chucker Interceptor Added: $chucker")
        return OkHttpClient.Builder()
            .addInterceptor(chucker)
            .addInterceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)
                Log.d("NetworkModule", "Request: ${request.url}")
                Log.d("NetworkModule", "Response: ${response.code}")
                response
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        client: OkHttpClient,
        gson: Gson,
        context: Context
    ): MovieRepository {
        return MovieRepository(client, gson, context)
    }

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context {
        return context // Application context provide karo
    }
}
