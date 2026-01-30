package com.rk.geofence.domain.model

import java.util.UUID

data class Geofence(
    val geofenceId: String = UUID.randomUUID().toString(),
    val name: String,
    val lat: Double,
    val long: Double,
    val radius: Float,
    val createdAt: Long = System.currentTimeMillis(),
    val isActive: Boolean = true
)
