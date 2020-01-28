package com.margge.dogami.presentation.detail

import com.margge.data.repository.GamesRepository
import com.margge.usecases.GetGameByIdUseCase
import com.margge.usecases.UpdateGameUseCase
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Module
class DetailActivityModule(private val gameId: Int) {

    @Provides
    fun getGameByIdUseCaseProvider(repository: GamesRepository) = GetGameByIdUseCase(repository)

    @Provides
    fun updateGameUseCaseProvider(repository: GamesRepository) = UpdateGameUseCase(repository)

    @Provides
    fun detailViewModelProvider(
        getGameByIdUseCase: GetGameByIdUseCase,
        updateGameUseCase: UpdateGameUseCase
    ): DetailViewModel {
        return DetailViewModel(gameId, getGameByIdUseCase, updateGameUseCase)
    }
}

@Subcomponent(modules = [DetailActivityModule::class])
interface DetailActivityComponent {
    val detailViewModel: DetailViewModel
}