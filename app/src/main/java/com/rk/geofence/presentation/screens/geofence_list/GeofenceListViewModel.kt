package com.rk.geofence.presentation.screens.geofence_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rk.geofence.domain.model.Geofence
import com.rk.geofence.domain.use_case.GetAllGeofencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GeofenceListViewModel @Inject constructor(
    getAllGeofencesUseCase: GetAllGeofencesUseCase
) : ViewModel() {

    val geofences: StateFlow<List<Geofence>> = getAllGeofencesUseCase()
        .stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        emptyList()
    )

}