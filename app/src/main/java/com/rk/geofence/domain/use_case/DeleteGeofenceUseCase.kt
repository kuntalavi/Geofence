package com.rk.geofence.domain.use_case

import com.rk.geofence.domain.repo.GeofenceRepo
import javax.inject.Inject

class DeleteGeofenceUseCase @Inject constructor(
    private val geofenceRepo: GeofenceRepo
) {
    suspend operator fun invoke(geofenceId: String) {
        geofenceRepo.deleteGeofence(geofenceId)
    }
}