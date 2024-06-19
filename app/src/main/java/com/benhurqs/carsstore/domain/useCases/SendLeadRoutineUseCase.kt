package com.benhurqs.carsstore.domain.useCases

import android.util.Log
import com.benhurqs.carsstore.domain.repository.LeadRepository

class SendLeadRoutineUseCase (
    private val repository: LeadRepository
) {

    suspend fun execute() {
        val leadsList = repository.getLeads()

        if (leadsList.isNotEmpty()) {
            try {

                val request = repository.sendLeadsToApi(leadsList)
                if (request) {
                    repository.clearDataLeads()
                }

            } catch (e: Exception) {
                Log.e("LEAD_ROUTINE", e.toString() )
            }
        }
    }
}
