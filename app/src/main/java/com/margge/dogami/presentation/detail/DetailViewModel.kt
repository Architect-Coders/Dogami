package com.margge.dogami.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.margge.dogami.Game

class DetailViewModel(private val game: Game) : ViewModel() {

    class UiModel(val game: Game)

    private val _model: MutableLiveData<UiModel> = MutableLiveData()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) _model.value = UiModel(game)
            return _model
        }
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val game: Game) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = DetailViewModel(game) as T
}