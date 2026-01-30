package com.rk.geofence.domain.model

data class Visit(
    val visitId: String,
    val geofenceId: String,
    val geofenceName: String,
    val entryTime: Long,
    val exitTime: Long?,
    val durationMilliseconds: Long
)
