package com.benhurqs.carsstore.data.repository

import com.benhurqs.carsstore.data.local.dao.LeadDao
import com.benhurqs.carsstore.data.local.entities.LeadDB
import com.benhurqs.carsstore.data.remote.CarService
import com.benhurqs.carsstore.domain.model.Lead
import com.benhurqs.carsstore.domain.repository.LeadRepository

class LeadRepositoryImpl(
    private val service: CarService,
    private val leadDao: LeadDao
) : LeadRepository {

    override suspend fun saveLead(lead: Lead) {
        leadDao.saveLead(
            LeadDB(
                carId = lead.carId,
                nomeLead = lead.nomeLead,
                emailLead = lead.emailLead
            )
        )
    }

    override suspend fun getLeads(): List<Lead> {
        val leads = leadDao.getLeads().map { leadDb ->
            leadDb.toLead()
        }
        return leads
    }

    override suspend fun sendLeadsToApi(leads: List<Lead>): Boolean {
        val request = service.sendLead(leads)
        return request.isSuccessful
    }

    override suspend fun clearDataLeads() {
        leadDao.clearDb()
    }
}
