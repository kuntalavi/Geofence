package com.rk.geofence.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.rk.geofence.data.room.entity.VisitEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VisitDao {
    @Insert
    suspend fun insertVisit(visit: VisitEntity)

    @Query(
        "SELECT * FROM visits WHERE geofenceId = :geofenceId AND exitTime IS NULL ORDER BY entryTime DESC LIMIT 1"
    )
    suspend fun getLastOpenVisit(geofenceId: String): VisitEntity?

    @Update
    suspend fun updateVisit(visit: VisitEntity)

    @Query("SELECT * FROM visits")
    fun getAllVisits(): Flow<List<VisitEntity>>
}