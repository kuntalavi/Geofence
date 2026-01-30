package com.rk.geofence.domain.use_case

import com.rk.geofence.domain.model.Geofence
import com.rk.geofence.domain.repo.GeofenceRepo
import javax.inject.Inject

class InsertGeofenceUseCase @Inject constructor(
    private val geofenceRepo: GeofenceRepo
) {
    suspend operator fun invoke(geofence: Geofence) {
        geofenceRepo.insertGeofence(geofence)
    }
}