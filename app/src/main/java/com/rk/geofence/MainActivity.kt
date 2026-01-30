package com.rk.geofence

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.rk.geofence.presentation.navigation.BotNavScreen
import com.rk.geofence.presentation.theme.GeofenceTheme
import com.rk.geofence.presentation.utils.RequestPermissions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeofenceTheme {
                var permissionGranted by remember { mutableStateOf(false) }
                if (permissionGranted) {
                    BotNavScreen()
                } else {
                    RequestPermissions(
                        onAllGranted = {
                            permissionGranted = true
                        }
                    )
                }
            }
        }
    }
}