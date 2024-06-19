package com.benhurqs.carsstore.presentation.di

import com.benhurqs.carsstore.presentation.home.CarHomeViewModel
import com.benhurqs.carsstore.presentation.lead.LeadViewModel
import com.benhurqs.carsstore.presentation.splash.CarSplashViewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {

    fun load() {
        loadKoinModules(viewModelModule())
    }

    private fun viewModelModule(): Module {

        return module {

            factory { CarHomeViewModel(carList = get()) }

            factory { LeadViewModel(leadUseCase = get()) }

            factory { CarSplashViewModel(leadUseCase = get()) }
        }
    }
}