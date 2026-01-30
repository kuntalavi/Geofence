package com.rk.geofence.data.geofence

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent
import com.rk.geofence.data.room.dao.GeofenceDao
import com.rk.geofence.data.room.dao.VisitDao
import com.rk.geofence.data.room.entity.VisitEntity
import com.rk.geofence.utils.DateUt.formatDuration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class GeofenceBroadcastReciever : BroadcastReceiver() {

    @Inject
    lateinit var geofenceDao: GeofenceDao

    @Inject
    lateinit var visitDao: VisitDao

    @Inject
    lateinit var geofenceVisitNotification: GeofenceVisitNotification

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onReceive(context: Context, intent: Intent) {
        val geofenceEvent = GeofencingEvent.fromIntent(intent)

        if (geofenceEvent == null || geofenceEvent.hasError()) {
            val eMessage =
                GeofenceStatusCodes.getStatusCodeString(geofenceEvent?.errorCode ?: -1)
            Log.e("Geofence", "e - $eMessage")
            return
        }

        val geofenceTransition = geofenceEvent.geofenceTransition

        val triggeringGeofences = geofenceEvent.triggeringGeofences

        val pendingResult = goAsync()

        scope.launch {
            try {
                if (triggeringGeofences != null) {
                    for (geofence in triggeringGeofences) {
                        handleTransition(
                            geofence.requestId,
                            geofenceTransition
                        )
                    }
                }
            } finally {
                pendingResult.finish()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private suspend fun handleTransition(
        geofenceId: String,
        transition: Int
    ) {
        val geofenceEntity = geofenceDao.getGeofenceById(geofenceId) ?: return
        val currentTime = System.currentTimeMillis()

        when (transition) {
            Geofence.GEOFENCE_TRANSITION_ENTER -> {
                Log.d("Geofence", "Entered - ${geofenceEntity.name}")

                val visitEntity = VisitEntity(
                    geofenceId = geofenceId,
                    geofenceName = geofenceEntity.name,
                    entryTime = currentTime
                )
                visitDao.insertVisit(visitEntity)

                geofenceVisitNotification.sendNotification(
                    "Visit At ${geofenceEntity.name}",
                    "Your Visit Time..."
                )
            }

            Geofence.GEOFENCE_TRANSITION_EXIT -> {
                Log.d("Geofence", "Exit - ${geofenceEntity.name}")

                val openVisit = visitDao.getLastOpenVisit(geofenceId)

                if (openVisit != null) {
                    val durationMilliseconds = currentTime - openVisit.entryTime

                    val completedVisit = openVisit.copy(
                        exitTime = currentTime,
                        durationMilliseconds = durationMilliseconds
                    )

                    visitDao.updateVisit(completedVisit)

                    geofenceVisitNotification.sendNotification(
                        "Visit Completed ${geofenceEntity.name}",
                        "Duration: ${formatDuration(durationMilliseconds)}"
                    )
                } else {
                    Log.w("Geofence", "No Open Visit Found - ${geofenceEntity.name}")
                }
            }

            else -> {
                Log.d("Geofence", "Unknown Transition $transition")
            }
        }
    }
}