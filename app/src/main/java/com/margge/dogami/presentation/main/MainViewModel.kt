package com.margge.dogami.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.margge.dogami.data.database.Game
import com.margge.dogami.data.server.GamesRepository
import com.margge.dogami.presentation.utils.ScopedViewModel
import kotlinx.coroutines.launch

class MainViewModel(private val gamesRepository: GamesRepository) : ScopedViewModel() {

    sealed class UiModel {
        object Loading : UiModel()
        class Content(val games: List<Game>) : UiModel()
        class Navigation(val game: Game) : UiModel()
    }

    private val _model: MutableLiveData<UiModel> = MutableLiveData()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) fetchGames()
            return _model
        }

    init {
        initScope()
    }

    private fun fetchGames() {
        launch {
            _model.value = UiModel.Loading
            _model.value = UiModel.Content(gamesRepository.getGamesList())
        }
    }

    fun onGameClicked(game: Game) {
        _model.value = UiModel.Navigation(game)
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}