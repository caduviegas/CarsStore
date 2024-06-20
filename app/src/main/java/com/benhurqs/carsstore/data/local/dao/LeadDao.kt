package com.benhurqs.carsstore.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benhurqs.carsstore.data.local.entities.LeadDB


@Dao
interface LeadDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveLead(lead: LeadDB)

    @Query("SELECT * FROM leaddb")
    suspend fun getLeads(): List<LeadDB>

    @Query("DELETE FROM leaddb")
    suspend fun clearDb()
}
