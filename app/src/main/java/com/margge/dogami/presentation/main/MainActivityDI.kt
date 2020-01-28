package com.margge.dogami.presentation.main

import com.margge.data.repository.GamesRepository
import com.margge.usecases.GetGamesUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class MainActivityModule {

    @Provides
    fun getGamesUseCaseProvider(repository: GamesRepository) = GetGamesUseCase(repository)

    @Provides
    fun mainViewModelProvider(getGamesUseCase: GetGamesUseCase) = MainViewModel(getGamesUseCase)
}

@Subcomponent(modules = [MainActivityModule::class])
interface MainActivityComponent {
    val mainViewModel: MainViewModel
}