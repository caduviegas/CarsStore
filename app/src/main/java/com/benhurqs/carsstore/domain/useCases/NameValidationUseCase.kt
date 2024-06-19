package com.benhurqs.carsstore.domain.useCases

import com.benhurqs.carsstore.domain.useCases.util.Validate

class NameValidationUseCase {

    operator fun invoke(name: String): Validate {

        if (name.isBlank()) {

            return Validate(
                successful = false,
                errorMessage = "Digite seu nome"
            )
        }
        return Validate(successful = true)
    }

}