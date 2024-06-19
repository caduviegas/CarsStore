package com.benhurqs.carsstore.domain.useCases

import android.util.Patterns
import com.benhurqs.carsstore.domain.useCases.util.Validate

class EmailValidationUseCase {

    operator fun invoke(email: String): Validate {

        if (email.isBlank()) {

            return Validate(
                successful = false,
                errorMessage = "Digite um e-mail"
            )
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            return Validate(
                successful = false,
                errorMessage = "Digite um e-mail válido"
            )
        }
        return Validate(successful = true)
    }
}