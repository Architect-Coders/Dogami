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

//    @Provides
//    fun mainViewModelProvider(
//        getGamesUseCase: GetGamesUseCase,
//        uiDispatcher: CoroutineDispatcher
//    ) = MainViewModel(getGamesUseCase, uiDispatcher)
}

//@Subcomponent(modules = [MainActivityModule::class])
//interface MainActivityComponent {
//    val mainViewModel: MainViewModel
//}