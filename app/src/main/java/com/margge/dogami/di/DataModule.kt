package com.margge.dogami.di

import com.margge.data.repository.GamesRepository
import com.margge.data.repository.LocationRepository
import com.margge.data.repository.PermissionHelper
import com.margge.data.source.LocalDataSource
import com.margge.data.source.LocationDataSource
import com.margge.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun providesLocationRepository(
        locationDataSource: LocationDataSource,
        permissionHelper: PermissionHelper
    ): LocationRepository {
        return LocationRepository(locationDataSource, permissionHelper)
    }

    @Provides
    fun gamesRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        locationRepository: LocationRepository
    ): GamesRepository {
        return GamesRepository(localDataSource, remoteDataSource, locationRepository)
    }
}