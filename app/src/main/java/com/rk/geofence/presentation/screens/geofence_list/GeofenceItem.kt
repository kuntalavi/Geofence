package com.rk.geofence.presentation.screens.geofence_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rk.geofence.R
import com.rk.geofence.domain.model.Geofence
import com.rk.geofence.presentation.utils.DateUt.formatDateTime
import java.util.Locale

@Composable
fun GeofenceItem(
    geofence: Geofence
) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.location_24px),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )

            Spacer(
                modifier = Modifier
                    .width(15.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = geofence.name
                )

                Text(
                    text = "Lat: ${
                        String.format(
                            Locale.getDefault(),
                            "%.4f",
                            geofence.lat
                        )
                    }, Lng: ${
                        String.format(
                            Locale.getDefault(),
                            "%.4f", geofence.long
                        )
                    }"
                )

                Text(
                    text = "Radius: ${geofence.radius} m"
                )

                Text(
                    text = "Created At: ${formatDateTime(geofence.createdAt)}"
                )
            }
        }
    }
}