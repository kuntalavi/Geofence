package com.rk.geofence.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "geofences")
data class GeofenceEntity(
    @PrimaryKey(autoGenerate = false)
    val geofenceId: String,
    val name: String,
    val lat: Double,
    val long: Double,
    val radius: Float,
    val createdAt: Long,
    val isActive: Boolean = true
)
