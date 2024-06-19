package com.benhurqs.carsstore.domain.useCases

import com.benhurqs.carsstore.domain.useCases.util.ValidationResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LeadValidationUseCase(
    private val emailValidationUseCase: EmailValidationUseCase,
    private val nameValidationUseCase: NameValidationUseCase
) {

    suspend operator fun invoke(email: String, name: String): Flow<ValidationResult> = flow {

        val emailValidation = emailValidationUseCase(email)
        val nameValidation = nameValidationUseCase(name)

        if (emailValidation.successful) {
            emit(ValidationResult.EmailSuccess)

        } else {
            emit(ValidationResult.EmailError(emailValidation.errorMessage!!))
        }

        if (nameValidation.successful) {
            emit(ValidationResult.NameSuccess)

        } else {
            emit(ValidationResult.NameError(nameValidation.errorMessage!!))
        }

        if (emailValidation.successful && nameValidation.successful) {
            emit(ValidationResult.Success("$name, seus dados foram enviados"))
        }
    }
}