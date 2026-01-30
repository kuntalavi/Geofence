package com.rk.geofence.presentation.screens.geofence_visits

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rk.geofence.domain.model.Visit
import com.rk.geofence.presentation.utils.DateUt.formatDateTime
import com.rk.geofence.presentation.utils.DateUt.formatDuration

@Composable
fun VisitItem(visit: Visit) {
    Card(
        elevation = CardDefaults.cardElevation(
            4.dp
        )
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = visit.geofenceName,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                if (visit.durationMilliseconds > 0) {
                    Text(
                        text = formatDuration(visit.durationMilliseconds),
                        style = MaterialTheme.typography.labelMedium
                    )
                } else {
                    Text(
                        text = "Ongoing",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = Color.White
                        )
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(
                        vertical = 10.dp
                    )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Entry",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color.Gray
                        )
                    )
                    Text(
                        text = formatDateTime(visit.entryTime),
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Exit",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color.Gray
                        )
                    )
                    if (visit.exitTime != null) {
                        Text(
                            text = formatDateTime(visit.exitTime),
                            style = MaterialTheme.typography.bodySmall
                        )
                    } else {
                        Text(
                            text = "-",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}