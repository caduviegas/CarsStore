package com.benhurqs.carsstore.domain.useCases

data class LeadUseCase(
    val leadValidation: LeadValidationUseCase,
    val saveLead: SaveLeadUseCase,
    val sendLeadRoutine: SendLeadRoutineUseCase
)