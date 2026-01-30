package com.rk.geofence.presentation.screens.map

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    viewmodel: MapViewModel = hiltViewModel()
) {
    val geofences by viewmodel.geofences.collectAsStateWithLifecycle()

    var showInsertGeofenceDialog by remember { mutableStateOf(false) }
    var selectedLocation by remember { mutableStateOf<LatLng?>(null) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(
                28.6139,
                77.2090
            ),
            10f
        )
    }

    val blurRadius by animateDpAsState(
        targetValue = if (showInsertGeofenceDialog) 10.dp else 0.dp,
        label = ""
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(blurRadius)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(
                    isMyLocationEnabled = true
                ),
                onMapLongClick = { latLng ->
                    selectedLocation = latLng
                    showInsertGeofenceDialog = true
                },
                onMapLoaded = {

                }
            ) {

                geofences.forEach { fence ->
                    val center = LatLng(fence.lat, fence.long)

                    Marker(
                        state = MarkerState(position = center),
                        title = fence.name,
                        snippet = "Radius: ${fence.radius} m"
                    )

                    Circle(
                        center = center,
                        radius = fence.radius.toDouble(),
                        fillColor = Color(0x220000FF),
                        strokeColor = Color.Blue,
                        strokeWidth = 2f
                    )
                }

            }
        }

        if (showInsertGeofenceDialog && selectedLocation != null) {
            InsertGeofenceDialog(
                onDismiss = {
                    showInsertGeofenceDialog = false
                },
                onConfirm = { name, radius ->
                    viewmodel.insertGeofence(
                        name,
                        selectedLocation!!,
                        radius
                    )
                    showInsertGeofenceDialog = false
                }
            )
        }
    }
}