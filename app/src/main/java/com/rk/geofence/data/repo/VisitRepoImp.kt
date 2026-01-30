package com.rk.geofence.data.repo

import com.rk.geofence.data.mapper.toDomain
import com.rk.geofence.data.room.dao.VisitDao
import com.rk.geofence.domain.model.Visit
import com.rk.geofence.domain.repo.VisitRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VisitRepoImp @Inject constructor(
    private val visitDao: VisitDao
) : VisitRepo {
    override fun getAllVisits(): Flow<List<Visit>> {
        return visitDao.getAllVisits().map { list ->
            list.map { entity ->
                entity.toDomain()
            }
        }
    }
}