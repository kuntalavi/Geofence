package com.rk.geofence.domain.repo

import com.rk.geofence.domain.model.Geofence
import kotlinx.coroutines.flow.Flow

interface GeofenceRepo {
    suspend fun insertGeofence(geofence: Geofence)
    suspend fun deleteGeofence(geofenceId: String)
    fun getAllGeofences(): Flow<List<Geofence>>
}