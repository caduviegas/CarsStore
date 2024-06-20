package com.benhurqs.carsstore.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.benhurqs.carsstore.domain.model.Lead


@Entity
data class LeadDB (

    @PrimaryKey(autoGenerate = true)
    val leadId: Long = 0,
    val carId: Long,
    val nomeLead: String,
    val emailLead: String

) {

    fun toLead(): Lead {
        return Lead(
            carId = carId,
            nomeLead = nomeLead,
            emailLead = emailLead
        )
    }
}