package com.rk.geofence.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Root {

    @Serializable
    data object GeofenceList : Root

    @Serializable
    data object Map : Root

    @Serializable
    data object GeofenceVisits : Root

}