package com.rk.geofence.domain.use_case

import com.rk.geofence.domain.model.Geofence
import com.rk.geofence.domain.repo.GeofenceRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllGeofencesUseCase @Inject constructor(
    private val geofenceRepo: GeofenceRepo
) {
    operator fun invoke(): Flow<List<Geofence>> {
        return geofenceRepo.getAllGeofences()
    }
}