package com.benhurqs.carsstore.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.benhurqs.carsstore.data.local.dao.LeadDao
import com.benhurqs.carsstore.data.local.entities.LeadDB

@Database(
    entities = [LeadDB::class],
    version = 1,
    exportSchema = false
)
abstract class LeadDataBase : RoomDatabase() {

    abstract val dao: LeadDao
    companion object {

        @Volatile
        private var INSTANCE: LeadDataBase? = null

        fun getInstance(context: Context): LeadDataBase {

            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        LeadDataBase::class.java,
                        "leads_db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
