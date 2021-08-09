package com.salazarev.hw19mvvmkotlindocokhttprxjava.view

import androidx.appcompat.app.AppCompatActivity
import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.JsonWorker
import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.client.ClientApi
import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.api.client.ClientApiImpl
import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.repository.ClientRepository
import com.salazarev.hw19mvvmkotlindocokhttprxjava.data.repository.ClientRepositoryImpl
import com.salazarev.hw19mvvmkotlindocokhttprxjava.domain.QuotationInteractor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

abstract class BaseActivity : AppCompatActivity() {
    protected fun getDependency(): QuotationInteractor {
        val moshi: Moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val jsonWorker = JsonWorker(moshi)

        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .writeTimeout(5000, TimeUnit.MILLISECONDS)
            .addNetworkInterceptor(HttpLoggingInterceptor())
            .build()

        val clientApi: ClientApi = ClientApiImpl(okHttpClient, jsonWorker)

        val repo: ClientRepository = ClientRepositoryImpl(clientApi)

        return QuotationInteractor(repo)
    }
}