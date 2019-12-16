package com.margge.dogami.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.margge.dogami.Game
import com.margge.dogami.data.NetworkHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val _model: MutableLiveData<UiModel> = MutableLiveData()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) fetchGames()
            return _model
        }

    sealed class UiModel {

        object Loading : UiModel()
        object Error : UiModel()
        class Content(val games: List<Game>) : UiModel()
        class Navigation(val game: Game) : UiModel()
    }

    private fun fetchGames() {
        NetworkHelper.tmdbApi().getListGames()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer { onSuccess(it) }, Consumer { onError(it) })
    }

    private fun onSuccess(response: List<Game>) {
        _model.value = UiModel.Content(response)
    }

    private fun onError(it: Throwable) {
        _model.value = UiModel.Error
    }


    fun onGameClicked(game:Game) {
        _model.value = UiModel.Navigation(game)
    }
}