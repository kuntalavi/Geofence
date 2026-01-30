package com.rk.geofence.presentation.screens.map

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertGeofenceDialog(
    onDismiss: () -> Unit,
    onConfirm: (name: String, radius: Float) -> Unit
) {

    var name by remember { mutableStateOf("") }
    var radiusS by remember { mutableStateOf("50") }

    BasicAlertDialog(
        onDismissRequest = onDismiss
    ) {

        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(0.dp),
            tonalElevation = 6.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(15.dp)
            ) {
                Text(
                    text = "Add Geofence"
                )

                TextField(
                    value = name,
                    onValueChange = { new ->
                        name = new
                    },
                    label = {
                        Text(
                            text = "Location Name"
                        )
                    }
                )

                TextField(
                    value = radiusS,
                    onValueChange = { r ->
                        radiusS = r
                    },
                    label = {
                        Text(
                            text = "Location Radius (meters)"
                        )
                    }
                )

                Row {
                    Button(
                        onClick = {
                            val radius = radiusS.toFloatOrNull() ?: 50f
                            if (name.isNotEmpty()) {
                                onConfirm(
                                    name,
                                    radius
                                )
                            }
                        }
                    ) {
                        Text(
                            text = "Save"
                        )
                    }

                    Button(
                        onClick = {
                            onDismiss()
                        }
                    ) {
                        Text(
                            text = "Cancel"
                        )
                    }
                }
            }
        }
    }

}