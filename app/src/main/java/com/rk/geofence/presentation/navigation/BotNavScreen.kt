package com.rk.geofence.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rk.geofence.R

data class BotNavItem(
    val route: Root,
    val icon: Painter
)

@Composable
fun BotNavScreen() {

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val botNavList = listOf(
        BotNavItem(
            route = Root.GeofenceList,
            icon = painterResource(R.drawable.list_24px)
        ),
        BotNavItem(
            route = Root.Map,
            icon = painterResource(R.drawable.map_24px)
        ),
        BotNavItem(
            route = Root.GeofenceVisits,
            icon = painterResource(R.drawable.history_24px)
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                botNavList.forEach { i ->
                    NavigationBarItem(
                        onClick = {
                            navController.navigate(
                                i.route
                            )
                        },
                        icon = {
                            Icon(
                                painter = i.icon,
                                contentDescription = ""
                            )
                        },
                        selected = currentDestination?.hasRoute(i.route::class) == true
                    )
                }
            }
        }
    ) { inner ->
        NavGraph(
            navController = navController,
            modifier = Modifier
                .fillMaxSize()
                .padding(inner)
        )
    }

}