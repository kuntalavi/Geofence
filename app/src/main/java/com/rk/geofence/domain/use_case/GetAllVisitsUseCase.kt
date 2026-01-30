package com.rk.geofence.domain.use_case

import com.rk.geofence.domain.model.Visit
import com.rk.geofence.domain.repo.VisitRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllVisitsUseCase @Inject constructor(
    private val visitRepo: VisitRepo
) {
    operator fun invoke(): Flow<List<Visit>> {
        return visitRepo.getAllVisits()
    }
}