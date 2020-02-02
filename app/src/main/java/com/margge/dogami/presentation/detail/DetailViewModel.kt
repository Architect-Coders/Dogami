package com.margge.dogami.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.margge.dogami.presentation.utils.ScopedViewModel
import com.margge.domain.Game
import com.margge.usecases.GetGameByIdUseCase
import com.margge.usecases.UpdateGameUseCase
import kotlinx.coroutines.launch

class DetailViewModel(
    private val gameId: Int,
    private val getGameByIdUseCase: GetGameByIdUseCase,
    private val updateGameUseCase: UpdateGameUseCase
) :
    ScopedViewModel() {

    class UiModel(val game: Game)

    private val _model: MutableLiveData<UiModel> = MutableLiveData()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findGame()
            return _model
        }

    private fun findGame() = launch {
        _model.value = UiModel((getGameByIdUseCase.invoke(gameId)))
    }

    fun onFavoriteGameClicked() = launch {
        _model.value?.game?.let {
            //val updatedGame = it.copy(favorite = !it.favorite)
            _model.value = UiModel(updateGameUseCase.invoke(it))
            //updateGameUseCase.invoke(updatedGame)
        }
    }
}