package com.rk.geofence.di

import com.rk.geofence.data.repo.GeofenceRepoImp
import com.rk.geofence.data.repo.VisitRepoImp
import com.rk.geofence.domain.repo.GeofenceRepo
import com.rk.geofence.domain.repo.VisitRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    @Singleton
    abstract fun geofenceRepo(
        geofenceRepoImp: GeofenceRepoImp
    ): GeofenceRepo

    @Binds
    @Singleton
    abstract fun visitRepo(
        visitRepoImp: VisitRepoImp
    ): VisitRepo

}