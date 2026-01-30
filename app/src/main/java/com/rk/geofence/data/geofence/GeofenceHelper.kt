package com.rk.geofence.data.geofence

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class GeofenceHelper @Inject constructor(
    @param:ApplicationContext private val context: Context,
    private val geofencingClient: GeofencingClient
) {

    private val geofencePendingIntent: PendingIntent by lazy {
        val intent = Intent(
            context,
            GeofenceBroadcastReciever::class.java
        )

        PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )
    }

    @SuppressLint("MissingPermission")
    fun insertGeofence(
        geofenceId: String,
        location: LatLng,
        radius: Float,
    ) {
        val geofence = Geofence.Builder()
            .setRequestId(geofenceId)
            .setCircularRegion(
                location.latitude,
                location.longitude,
                radius
            )
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .setTransitionTypes(
                Geofence.GEOFENCE_TRANSITION_ENTER or
                        Geofence.GEOFENCE_TRANSITION_EXIT
            )

        val geofenceRequest = GeofencingRequest.Builder()
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            .addGeofence(
                geofence.build()
            )
            .build()

        geofencingClient.addGeofences(geofenceRequest, geofencePendingIntent)
            .addOnSuccessListener {
                Log.d("Geofence", "Geofence Added - $geofenceId")
            }
            .addOnFailureListener { e ->
                Log.e("Geofence", "e - ${e.message} - $geofenceId")
            }
    }

    fun removeGeofence(
        geofenceId: String
    ) {
        geofencingClient.removeGeofences(
            listOf(
                geofenceId
            )
        )
    }

}