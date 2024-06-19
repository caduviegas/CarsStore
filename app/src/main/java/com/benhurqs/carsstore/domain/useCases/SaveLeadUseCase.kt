package com.benhurqs.carsstore.domain.useCases

import com.benhurqs.carsstore.domain.model.Lead
import com.benhurqs.carsstore.domain.repository.LeadRepository

class SaveLeadUseCase(
    private val repository: LeadRepository
) {

    suspend operator fun invoke(lead: Lead) {
        repository.saveLead(lead)
    }
}