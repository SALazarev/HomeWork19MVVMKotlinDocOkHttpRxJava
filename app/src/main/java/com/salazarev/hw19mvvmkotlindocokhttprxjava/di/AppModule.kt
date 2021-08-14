package com.salazarev.hw19mvvmkotlindocokhttprxjava.di

import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.client.ClientApi
import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.client.ClientApiImpl
import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.repository.ClientRepository
import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.repository.ClientRepositoryImpl
import com.salazarev.hw19mvvmkotlindocokhttprxjava.util.json.JsonWorker
import com.salazarev.hw19mvvmkotlindocokhttprxjava.util.json.JsonWorkerImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
internal interface AppModule {
    companion object {
        @Provides
        fun provideMoshi(): Moshi {
            return Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        }

        @Provides
        fun provideOkHttp(): OkHttpClient {
            return OkHttpClient().newBuilder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(HttpLoggingInterceptor())
                .build()
        }
    }

    @Binds
    fun bindClientRepository(repo: ClientRepositoryImpl): ClientRepository

    @Binds
    fun bindClientApi(repo: ClientApiImpl): ClientApi

    @Binds
    fun bindJsonWorker(repo: JsonWorkerImpl): JsonWorker
}