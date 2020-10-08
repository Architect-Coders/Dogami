package com.margge.dogami.presentation.main

import com.margge.data.repository.GamesRepository
import com.margge.usecases.GetGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class MainActivityModule {

    @Provides
    fun getGamesUseCaseProvider(repository: GamesRepository) = GetGamesUseCase(repository)
}