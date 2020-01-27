package com.margge.dogami.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.margge.data.repository.GamesRepository
import com.margge.dogami.R
import com.margge.dogami.data.database.RoomDataSource
import com.margge.dogami.data.server.DogamiDataSource
import com.margge.dogami.presentation.detail.DetailActivity
import com.margge.dogami.presentation.main.MainViewModel.UiModel
import com.margge.dogami.presentation.main.adapter.GameAdapter
import com.margge.dogami.presentation.utils.SpacesItemDecoration
import com.margge.dogami.presentation.utils.app
import com.margge.dogami.presentation.utils.getViewModel
import com.margge.dogami.presentation.utils.startActivity
import com.margge.usecases.GetGamesUseCase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = getViewModel {
            MainViewModel(
                GetGamesUseCase(
                    GamesRepository(
                        RoomDataSource(app.db),
                        DogamiDataSource()
                    )
                )
            )
        }

        adapter = GameAdapter(viewModel::onGameClicked)
        gamesRecycler.adapter = adapter
        gamesRecycler.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val decoration = SpacesItemDecoration(50)
        gamesRecycler.addItemDecoration(decoration)

        viewModel.model.observe(this, Observer(::updateUI))
    }

    private fun updateUI(game: UiModel) {
        when (game) {
            is UiModel.Content -> adapter.games = game.games
            is UiModel.Navigation -> startActivity<DetailActivity> {
                putExtra(DetailActivity.GAME, game.game.id)
            }
        }
    }
}
