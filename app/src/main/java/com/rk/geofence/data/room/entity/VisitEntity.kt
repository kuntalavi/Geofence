package com.rk.geofence.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "visits")
data class VisitEntity(
    @PrimaryKey(autoGenerate = false)
    val visitId: String = UUID.randomUUID().toString(),
    val geofenceId: String,
    val geofenceName: String,
    val entryTime: Long,
    val exitTime: Long? = null,
    val durationMilliseconds: Long = 0L
)
