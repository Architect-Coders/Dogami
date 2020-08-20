package com.margge.dogami.presentation.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.margge.dogami.R
import com.margge.dogami.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_detail.*

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val GAME = "DetailActivity:game"
    }

    //private lateinit var component: DetailActivityComponent
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //component = app.component.plus(DetailActivityModule(intent.getIntExtra(GAME, -1)))

        val binding: ActivityDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.detailViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.model.observe(this, Observer(::observeViewModel))
        favoriteGameButton.setOnClickListener { viewModel.onFavoriteGameClicked() }
    }

    private fun observeViewModel(model: DetailViewModel.UiModel) = with(model.game) {
        viewModel.updateUI(this)
    }
}