package com.benhurqs.carsstore.data.di

import android.util.Log
import com.benhurqs.carsstore.data.local.database.LeadDataBase
import com.benhurqs.carsstore.data.remote.CarService
import com.benhurqs.carsstore.data.repository.CarRepositoryImpl
import com.benhurqs.carsstore.data.repository.LeadRepositoryImpl
import com.benhurqs.carsstore.domain.repository.CarRepository
import com.benhurqs.carsstore.domain.repository.LeadRepository
import com.benhurqs.carsstore.util.Constants.BASE_URL
import com.benhurqs.carsstore.util.Constants.OK_HTTP
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DataModule {

    fun load() {
        loadKoinModules(networkModule() + repositoryModules() + localModule())
    }

    private fun repositoryModules(): Module {

        return module {

            single<CarRepository> { CarRepositoryImpl(service = get()) }

            single<LeadRepository> { LeadRepositoryImpl(service = get(), leadDao = get()) }

        }
    }

    private fun localModule(): Module {

        return module {

            single { LeadDataBase.getInstance(androidContext()).dao }

        }
    }

    private fun networkModule(): Module {

        return module {

            single<CarService> { createService(factory = get(), client = get()) }

            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e(OK_HTTP, it)
                }
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            }

        }
    }

    private inline fun <reified T> createService(
        factory: Moshi,
        client: OkHttpClient
    ): T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // Url base da Api
            .addConverterFactory(MoshiConverterFactory.create(factory)) // Conversor de Json
            .client(client) // Ok Http Interceptor
            .build() // Criação do Retrofit
            .create(T::class.java) // Criação do serviço
    }
}