package com.rk.geofence

import android.app.Application
import com.rk.geofence.data.geofence.GeofenceVisitNotification
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var geofenceVisitNotification: GeofenceVisitNotification

    override fun onCreate() {
        super.onCreate()
        geofenceVisitNotification.createNotificationChannel()
    }

}