package com.rk.geofence.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rk.geofence.data.room.dao.GeofenceDao
import com.rk.geofence.data.room.dao.VisitDao
import com.rk.geofence.data.room.entity.GeofenceEntity
import com.rk.geofence.data.room.entity.VisitEntity

@Database(
    entities = [
        GeofenceEntity::class,
        VisitEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class GeofenceDatabase : RoomDatabase() {
    abstract fun geofenceDao(): GeofenceDao
    abstract fun visitDao(): VisitDao
}