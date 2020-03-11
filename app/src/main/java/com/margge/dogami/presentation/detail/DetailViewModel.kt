package com.margge.dogami.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.margge.dogami.presentation.utils.ScopedViewModel
import com.margge.domain.Game
import com.margge.usecases.GetGameByIdUseCase
import com.margge.usecases.UpdateGameUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailViewModel(
    private val gameId: Int,
    private val getGameByIdUseCase: GetGameByIdUseCase,
    private val updateGameUseCase: UpdateGameUseCase,
    override val uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    data class UiModel(val game: Game)

    private val _model: MutableLiveData<UiModel> = MutableLiveData()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findGame()
            return _model
        }

    private val _gameName: MutableLiveData<String> = MutableLiveData()
    val gameName: LiveData<String> get() = _gameName

    private val _playersNumber: MutableLiveData<String> = MutableLiveData()
    val playersNumber: LiveData<String> get() = _playersNumber

    private val _gameDuration: MutableLiveData<String> = MutableLiveData()
    val gameDuration: LiveData<String> get() = _gameDuration

    private val _gameComplexity: MutableLiveData<String> = MutableLiveData()
    val gameComplexity: LiveData<String> get() = _gameComplexity

    private val _gameDescription: MutableLiveData<String> = MutableLiveData()
    val gameDescription: LiveData<String> get() = _gameDescription

    private val _gameUrl: MutableLiveData<String> = MutableLiveData()
    val gameUrl: LiveData<String> get() = _gameUrl

    private val _gameFavorite = MutableLiveData<Boolean>()
    val gameFavorite: LiveData<Boolean> get() = _gameFavorite

    init {
        initScope()
    }

    private fun findGame() = launch {
        _model.value = UiModel(getGameByIdUseCase.invoke(gameId))
    }

    fun onFavoriteGameClicked() = launch {
        model.value?.game?.let {
            _model.value = UiModel(updateGameUseCase.invoke(it))
        }
    }

    fun updateUI(game: Game) {
        with(game) {
            _gameName.value = name
            _playersNumber.value = "$minPlayers-$maxPlayers"
            _gameDuration.value = time
            _gameComplexity.value = complexity
            _gameDescription.value = description
            _gameUrl.value = posterUrl
            _gameFavorite.value = favorite
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}