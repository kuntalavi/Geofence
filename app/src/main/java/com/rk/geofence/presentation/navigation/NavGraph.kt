package com.rk.geofence.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rk.geofence.presentation.screens.geofence_list.GeofenceListScreen
import com.rk.geofence.presentation.screens.geofence_visits.GeofenceVisitsScreen
import com.rk.geofence.presentation.screens.map.MapScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Root.GeofenceList,
        modifier = modifier
    ) {
        composable<Root.GeofenceList> {
            GeofenceListScreen()
        }
        composable<Root.Map> {
            MapScreen()
        }
        composable<Root.GeofenceVisits> {
            GeofenceVisitsScreen()
        }
    }
}
