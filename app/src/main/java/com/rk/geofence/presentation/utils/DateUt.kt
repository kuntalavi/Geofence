package com.rk.geofence.presentation.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUt {

    fun formatDateTime(timestamp: Long): String {
        if (timestamp == 0L) return "--"
        val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        return sdf.format(
            Date(timestamp)
        )
    }

    fun formatDuration(durationMilliseconds: Long): String {
        val seconds = durationMilliseconds / 1000
        val m = (seconds % 3600) / 60
        val s = seconds % 60
        val h = seconds / 3600

        return if (h > 0) "$h h $m m" else "$m m $s s"
    }

}