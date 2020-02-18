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

    init {
        initScope()
    }

    private fun findGame() = launch {
        _model.value = UiModel(getGameByIdUseCase.invoke(gameId))
    }

    fun onFavoriteGameClicked() = launch {
        _model.value?.game?.let {
            _model.value = UiModel(updateGameUseCase.invoke(it))
        }
    }

    override fun onCleared() {
        destroyScope()
        super.onCleared()
    }
}