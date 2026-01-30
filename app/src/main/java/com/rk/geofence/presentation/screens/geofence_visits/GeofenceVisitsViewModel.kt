package com.rk.geofence.presentation.screens.geofence_visits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rk.geofence.domain.model.Visit
import com.rk.geofence.domain.use_case.GetAllVisitsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GeofenceVisitsViewModel @Inject constructor(
    getAllVisitsUseCase: GetAllVisitsUseCase
) : ViewModel() {

    val visits: StateFlow<List<Visit>> = getAllVisitsUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        emptyList()
    )

}