package com.rk.geofence.di

import android.content.Context
import androidx.room.Room
import com.rk.geofence.data.room.GeofenceDatabase
import com.rk.geofence.data.room.dao.GeofenceDao
import com.rk.geofence.data.room.dao.VisitDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideGeofenceDatabase(
        @ApplicationContext context: Context
    ): GeofenceDatabase {
        return Room.databaseBuilder(
            context,
            GeofenceDatabase::class.java,
            "geofence_DB"
        ).build()
    }

    @Singleton
    @Provides
    fun provideGeofenceDao(
        database: GeofenceDatabase
    ): GeofenceDao = database.geofenceDao()

    @Singleton
    @Provides
    fun provideVisitDao(
        database: GeofenceDatabase
    ): VisitDao = database.visitDao()

}