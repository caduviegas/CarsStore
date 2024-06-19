package com.benhurqs.carsstore.domain.repository

import com.benhurqs.carsstore.domain.model.Lead

interface LeadRepository {

    suspend fun saveLead(lead: Lead)

    suspend fun getLeads(): List<Lead>

    suspend fun sendLeadsToApi(leads: List<Lead>): Boolean

    suspend fun clearDataLeads()

}