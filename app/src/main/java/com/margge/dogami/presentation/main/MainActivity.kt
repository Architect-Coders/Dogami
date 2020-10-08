package com.margge.dogami.presentation.main

import android.Manifest
import android.os.Bundle
import android.view.View.GONE
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.margge.dogami.R
import com.margge.dogami.presentation.detail.DetailActivity
import com.margge.dogami.presentation.main.MainViewModel.UiModel
import com.margge.dogami.presentation.main.adapter.GameAdapter
import com.margge.dogami.presentation.utils.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: GameAdapter
    private val viewModel: MainViewModel by viewModels()

    private val permissionRequester =
        PermissionRequester(this, Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupAdapter()
        viewModel.model.observe(this, Observer(::updateUI))
    }

    private fun setupAdapter() {
        adapter = GameAdapter(viewModel::onGameClicked)
        gamesRecycler.adapter = adapter
        gamesRecycler.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val decoration = SpacesItemDecoration(50)
        gamesRecycler.addItemDecoration(decoration)
    }

    private fun updateUI(game: UiModel) {
        when (game) {
            UiModel.RequestLocationPermission -> permissionRequester.request {
                viewModel.onCoarsePermissionRequested()
            }
            is UiModel.Content -> adapter.games = game.games
            is UiModel.Loading -> progressBar.visibility = GONE
            is UiModel.Navigation -> startActivity<DetailActivity> {
                putExtra(DetailActivity.GAME, game.game.id)
            }
        }
    }
}
