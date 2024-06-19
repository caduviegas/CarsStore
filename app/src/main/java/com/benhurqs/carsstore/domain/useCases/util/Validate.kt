package com.benhurqs.carsstore.domain.useCases.util

data class Validate(
    val successful: Boolean,
    val errorMessage: String? = null
)