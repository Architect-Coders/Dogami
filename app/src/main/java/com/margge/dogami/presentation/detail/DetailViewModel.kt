package com.margge.dogami.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.margge.dogami.data.database.Game
import com.margge.dogami.data.server.GamesRepository
import com.margge.dogami.presentation.utils.ScopedViewModel
import kotlinx.coroutines.launch

class DetailViewModel(private val gameId: Int, private val gamesRepository: GamesRepository) :
    ScopedViewModel() {

    class UiModel(val game: Game)

    private val _model: MutableLiveData<UiModel> = MutableLiveData()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findGame()
            return _model
        }

    private fun findGame() = launch {
        _model.value = UiModel((gamesRepository.findById(gameId)))
    }

    fun onFavoriteGameClicked() = launch {
        _model.value?.game?.let {
            val updatedGame = it.copy(isFavorite = !it.isFavorite)
            _model.value = UiModel(updatedGame)
            gamesRepository.update(updatedGame)
        }
    }
}