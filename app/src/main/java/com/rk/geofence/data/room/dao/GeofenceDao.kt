package com.rk.geofence.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rk.geofence.data.room.entity.GeofenceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GeofenceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGeofence(geofence: GeofenceEntity)

    @Query("SELECT * FROM geofences WHERE geofenceId = :geofenceId LIMIT 1")
    suspend fun getGeofenceById(geofenceId: String): GeofenceEntity?

    @Query("DELETE FROM geofences WHERE geofenceId = :geofenceId")
    suspend fun deleteGeofence(geofenceId: String)

    @Query("SELECT * FROM geofences")
    fun getAllGeofences(): Flow<List<GeofenceEntity>>
}