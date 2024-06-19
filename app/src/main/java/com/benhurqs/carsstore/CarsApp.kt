package com.benhurqs.carsstore

import android.app.Application
import com.benhurqs.carsstore.data.di.DataModule
import com.benhurqs.carsstore.domain.di.DomainModule
import com.benhurqs.carsstore.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class CarsApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CarsApp)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}