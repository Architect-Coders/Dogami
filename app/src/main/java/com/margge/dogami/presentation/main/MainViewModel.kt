package com.margge.dogami.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.margge.dogami.Game
import com.margge.dogami.data.GamesRepository
import com.margge.dogami.utils.Scope
import kotlinx.coroutines.launch

class MainViewModel(private val gamesRepository: GamesRepository) : ViewModel(),
    Scope by Scope.Impl() {

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

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val gamesRepository: GamesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = MainViewModel(gamesRepository) as T
}