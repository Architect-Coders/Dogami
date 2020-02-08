package com.margge.dogami.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.margge.dogami.presentation.utils.ScopedViewModel
import com.margge.domain.Game
import com.margge.usecases.GetGamesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class MainViewModel(
    private val getGamesUseCase: GetGamesUseCase,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val games: List<Game>) : UiModel()
        class Navigation(val game: Game) : UiModel()
        object RequestLocationPermission : UiModel()
    }

    private val _model: MutableLiveData<UiModel> = MutableLiveData()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) refresh()
            return _model
        }

    init {
        initScope()
    }

    private fun refresh() {
        _model.value = UiModel.RequestLocationPermission
    }

    fun onGameClicked(game: Game) {
        _model.value = UiModel.Navigation(game)
    }

    fun onCoarsePermissionRequested() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(getGamesUseCase.invoke())
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}