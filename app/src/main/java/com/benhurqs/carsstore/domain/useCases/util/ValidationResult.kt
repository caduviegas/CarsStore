package com.benhurqs.carsstore.domain.useCases.util

sealed class ValidationResult {
    class EmailError(val errorMessage: String) : ValidationResult()
    object EmailSuccess: ValidationResult()

    class NameError(val errorMessage: String) : ValidationResult()
    object NameSuccess: ValidationResult()

    class Success(val message: String) : ValidationResult()
}