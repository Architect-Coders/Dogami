package com.margge.dogami.presentation.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.margge.dogami.utils.loadUrl
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.gamePosterImageView

class DetailActivity : AppCompatActivity() {

    companion object {
        const val GAME = "DetailActivity:game"
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.margge.dogami.R.layout.activity_detail)

        viewModel = ViewModelProviders.of(
            this,
            DetailViewModelFactory(intent.getParcelableExtra(GAME))
        )[DetailViewModel::class.java]

        viewModel.model.observe(this, Observer(::updateUI))
    }

    private fun updateUI(model: DetailViewModel.UiModel) = with(model.game) {
        gamePosterImageView.loadUrl(model.game.posterUrl)
        gameDetailToolbar.title = model.game.name
        playersGameTextView.text =
            "Cantidad de jugadores: ${model.game.minPlayers}-${model.game.maxPlayers} "
        gameDurationTextView.text = "Duración de partida: ${model.game.time} "
        gameComplexityTextView.text = "Complejidad: ${model.game.complexity} "
        gameNameTextView.text = model.game.name
        gameDescriptionTextView.text = model.game.description
    }
}
