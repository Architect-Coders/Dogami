package com.margge.dogami.presentation.detail

import android.app.Activity
import com.margge.data.repository.GamesRepository
import com.margge.usecases.GetGameByIdUseCase
import com.margge.usecases.UpdateGameUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
class DetailActivityModule {

    @Provides
    @Named("gameId")
    fun getGameId(activity: Activity) = activity.intent.getIntExtra(DetailActivity.GAME, -1)

}

@Module
@InstallIn(ActivityRetainedComponent::class)
class DetailActivityRetainedModule {

    @Provides
    fun getGameByIdUseCaseProvider(repository: GamesRepository) = GetGameByIdUseCase(repository)

    @Provides
    fun updateGameUseCaseProvider(repository: GamesRepository) = UpdateGameUseCase(repository)

}