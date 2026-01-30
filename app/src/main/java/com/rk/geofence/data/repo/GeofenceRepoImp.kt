package com.rk.geofence.data.repo

import com.google.android.gms.maps.model.LatLng
import com.rk.geofence.data.geofence.GeofenceHelper
import com.rk.geofence.data.mapper.toDomain
import com.rk.geofence.data.mapper.toEntity
import com.rk.geofence.data.room.dao.GeofenceDao
import com.rk.geofence.domain.model.Geofence
import com.rk.geofence.domain.repo.GeofenceRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GeofenceRepoImp @Inject constructor(
    private val geofenceDao: GeofenceDao,
    private val geofenceHelper: GeofenceHelper
) : GeofenceRepo {
    override suspend fun insertGeofence(geofence: Geofence) {
        geofenceHelper.insertGeofence(
            geofenceId = geofence.geofenceId,
            location = LatLng(
                geofence.lat,
                geofence.long
            ),
            radius = geofence.radius
        )

        val geofenceEntity = geofence.toEntity()

        geofenceDao.insertGeofence(geofenceEntity)
    }

    override suspend fun deleteGeofence(geofenceId: String) {
        geofenceHelper.removeGeofence(geofenceId)
        geofenceDao.deleteGeofence(geofenceId)
    }

    override fun getAllGeofences(): Flow<List<Geofence>> {
        return geofenceDao.getAllGeofences().map { list ->
            list.map { entity ->
                entity.toDomain()
            }
        }
    }
}