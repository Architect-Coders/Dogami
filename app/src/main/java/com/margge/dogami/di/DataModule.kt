package com.margge.dogami.di

import com.margge.data.repository.GamesRepository
import com.margge.data.source.LocalDataSource
import com.margge.data.source.RemoteDataSource
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun gamesRepositoryProvider(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ): GamesRepository {
        return GamesRepository(localDataSource, remoteDataSource)
    }
}