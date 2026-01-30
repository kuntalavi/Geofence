package com.rk.geofence.data.mapper

import com.rk.geofence.data.room.entity.GeofenceEntity
import com.rk.geofence.domain.model.Geofence

fun GeofenceEntity.toDomain(): Geofence {
    return Geofence(
        geofenceId = geofenceId,
        name = name,
        lat = lat,
        long = long,
        radius = radius,
        createdAt = createdAt,
        isActive = isActive
    )
}

fun Geofence.toEntity(): GeofenceEntity {
    return GeofenceEntity(
        geofenceId = geofenceId,
        name = name,
        lat = lat,
        long = long,
        radius = radius,
        createdAt = createdAt,
        isActive = isActive
    )
}