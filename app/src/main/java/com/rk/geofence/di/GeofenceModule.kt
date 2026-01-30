package com.rk.geofence.di

import android.content.Context
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationServices
import com.rk.geofence.data.geofence.GeofenceHelper
import com.rk.geofence.data.geofence.GeofenceVisitNotification
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GeofenceModule {

    @Singleton
    @Provides
    fun provideGeofenceClient(
        @ApplicationContext context: Context
    ): GeofencingClient {
        return LocationServices.getGeofencingClient(
            context
        )
    }

    @Singleton
    @Provides
    fun provideGeofenceHelper(
        @ApplicationContext context: Context,
        geofencingClient: GeofencingClient
    ): GeofenceHelper {
        return GeofenceHelper(
            context,
            geofencingClient
        )
    }

    @Singleton
    @Provides
    fun provideGeofenceVisitNotification(
        @ApplicationContext context: Context
    ): GeofenceVisitNotification {
        return GeofenceVisitNotification(
            context
        )
    }

}