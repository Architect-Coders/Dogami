package com.margge.dogami.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.margge.dogami.presentation.detail.DetailActivity
import com.margge.dogami.presentation.main.adapter.GameAdapter
import com.margge.dogami.presentation.main.MainViewModel.UiModel
import kotlinx.android.synthetic.main.activity_main.*
import com.margge.dogami.utils.SpacesItemDecoration
import com.margge.dogami.utils.showToast
import com.margge.dogami.utils.startActivity

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: GameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.margge.dogami.R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        adapter = GameAdapter(viewModel::onGameClicked)
        gamesRecycler.adapter = adapter
        gamesRecycler.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val decoration = SpacesItemDecoration(16)
        gamesRecycler.addItemDecoration(decoration)

        viewModel.model.observe(this, Observer(::updateUI))
    }

    private fun updateUI(game: UiModel) {
        when (game) {
            is UiModel.Content -> adapter.games = game.games
            is UiModel.Error -> showToast("Error loading games")
            is UiModel.Navigation -> startActivity<DetailActivity> {
                putExtra(DetailActivity.GAME, game.game)
            }
        }
    }
}
