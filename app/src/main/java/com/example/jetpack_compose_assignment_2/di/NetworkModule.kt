package com.example.jetpack_compose_assignment_2.di

import com.example.jetpack_compose_assignment_2.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

// Tell Hilt this is a DI module, and its bindings live for the entire app lifecycle
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Base URL for all API calls
    private const val BASE_URL = "https://jsonplaceholder.typicode.com"

    // Provide a logging interceptor to print full request & response bodies
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY // show me the whole body
        }

    // Provide a singleton OkHttpClient configured with timeouts and our logger
    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor, // injected above
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)           // logs network traffic
        .connectTimeout(30, TimeUnit.SECONDS)        // max time to establish a connection
        .readTimeout(30, TimeUnit.SECONDS)           // max time to read data
        .writeTimeout(30, TimeUnit.SECONDS)          // max time to send data
        .build()

    // Provide a singleton Retrofit instance using our OkHttpClient and JSON converter
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)                          // the API’s root endpoint
            .client(okHttpClient)                       // attach custom HTTP client
            .addConverterFactory(GsonConverterFactory.create()) // auto (de)serialize JSON
            .build()

    // Provide the ApiService implementation generated by Retrofit
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)         // Retrofit generates this for us
}
