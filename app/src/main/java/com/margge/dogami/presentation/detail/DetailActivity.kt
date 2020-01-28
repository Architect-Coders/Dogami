package com.margge.dogami.presentation.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.margge.dogami.R
import com.margge.dogami.presentation.utils.app
import com.margge.dogami.presentation.utils.getViewModel
import com.margge.dogami.presentation.utils.loadUrl
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val GAME = "DetailActivity:game"
    }

    private lateinit var component: DetailActivityComponent
    private val viewModel: DetailViewModel by lazy { getViewModel { component.detailViewModel } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        component = app.component.plus(DetailActivityModule(intent.getIntExtra(GAME, -1)))

        viewModel.model.observe(this, Observer(::updateUI))
        favoriteGameButton.setOnClickListener { viewModel.onFavoriteGameClicked() }
    }

    private fun updateUI(model: DetailViewModel.UiModel) = with(model.game) {
        gamePosterImageView.loadUrl(model.game.posterUrl, R.drawable.default_board_game_detail)
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

        val favoriteIcon =
            if (favorite) R.drawable.ic_favorite_selected else R.drawable.ic_favorite_unselected
        favoriteGameButton.setImageDrawable(getDrawable(favoriteIcon))
    }
}
