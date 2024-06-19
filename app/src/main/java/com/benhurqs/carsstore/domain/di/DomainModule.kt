package com.benhurqs.carsstore.domain.di

import com.benhurqs.carsstore.domain.useCases.EmailValidationUseCase
import com.benhurqs.carsstore.domain.useCases.GetCarListUseCase
import com.benhurqs.carsstore.domain.useCases.LeadUseCase
import com.benhurqs.carsstore.domain.useCases.LeadValidationUseCase
import com.benhurqs.carsstore.domain.useCases.NameValidationUseCase
import com.benhurqs.carsstore.domain.useCases.SaveLeadUseCase
import com.benhurqs.carsstore.domain.useCases.SendLeadRoutineUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {

        return module {
            factory { GetCarListUseCase(repository = get()) }

            factory { SaveLeadUseCase(repository = get()) }

            factory { EmailValidationUseCase() }

            factory { NameValidationUseCase() }

            factory {
                LeadValidationUseCase(
                    emailValidationUseCase = get(),
                    nameValidationUseCase = get()
                )
            }
            factory { SendLeadRoutineUseCase(repository = get()) }

            factory {
                LeadUseCase(
                    leadValidation = get(),
                    saveLead = get(),
                    sendLeadRoutine = get()
                )
            }
        }
    }
}
