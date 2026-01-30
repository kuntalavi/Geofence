package com.rk.geofence.domain.repo

import com.rk.geofence.domain.model.Visit
import kotlinx.coroutines.flow.Flow

interface VisitRepo {
    fun getAllVisits(): Flow<List<Visit>>
}