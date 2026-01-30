package com.rk.geofence.data.mapper

import com.rk.geofence.data.room.entity.VisitEntity
import com.rk.geofence.domain.model.Visit

fun VisitEntity.toDomain(): Visit {
    return Visit(
        visitId = visitId,
        geofenceId = geofenceId,
        geofenceName = geofenceName,
        entryTime = entryTime,
        exitTime = exitTime,
        durationMilliseconds = durationMilliseconds
    )
}