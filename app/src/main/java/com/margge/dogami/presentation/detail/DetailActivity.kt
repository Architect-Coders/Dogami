package com.margge.dogami.presentation.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.margge.dogami.R
import com.margge.dogami.data.server.GamesRepository
import com.margge.dogami.presentation.utils.app
import com.margge.dogami.presentation.utils.getViewModel
import com.margge.dogami.presentation.utils.loadUrl
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val GAME = "DetailActivity:game"
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel = getViewModel {
            DetailViewModel(intent.getIntExtra(GAME, -1), GamesRepository(app))
        }

        viewModel.model.observe(this, Observer(::updateUI))
    }

    private fun updateUI(model: DetailViewModel.UiModel) = with(model.game) {
        if (model.game.posterUrl == null)
            gamePosterImageView.loadUrl("https://i.imgur.com/xY1A7nM.png")
        else
            gamePosterImageView.loadUrl(model.game.posterUrl)

        gameDetailToolbar.title = model.game.name
        playersGameTextView.text = getString(
            R.string.detail_game_players_number,
            "${model.game.minPlayers}-${model.game.maxPlayers}"
        )
        gameDurationTextView.text = getString(R.string.detail_game_time, "${model.game.time} ")
        gameComplexityTextView.text =
            getString(R.string.detail_game_complexity, "${model.game.complexity} ")
        gameNameTextView.text = model.game.name
        gameDescriptionTextView.text = model.game.description
    }
}
