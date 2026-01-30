package com.rk.geofence.presentation.screens.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.rk.geofence.domain.model.Geofence
import com.rk.geofence.domain.use_case.GetAllGeofencesUseCase
import com.rk.geofence.domain.use_case.InsertGeofenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    getAllGeofencesUseCase: GetAllGeofencesUseCase,
    private val insertGeofenceUseCase: InsertGeofenceUseCase
) : ViewModel() {

    val geofences: StateFlow<List<Geofence>> = getAllGeofencesUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            emptyList()
        )

    fun insertGeofence(
        name: String,
        location: LatLng,
        radius: Float
    ) {
        // e
        viewModelScope.launch {
            val geofence = Geofence(
                name = name,
                lat = location.latitude,
                long = location.longitude,
                radius = radius
            )

            insertGeofenceUseCase(geofence)
        }

    }

}