package com.margge.dogami.presentation.detail

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.margge.dogami.R
import com.margge.dogami.presentation.utils.loadUrl

@BindingAdapter("url")
fun ImageView.bindUrl(url: String?) {
    if (url != null)
        loadUrl(url, R.drawable.default_board_game_detail)
}

@BindingAdapter("favorite")
fun FloatingActionButton.setFavorite(favorite: Boolean?) {
    val icon =
        when (favorite) {
            true -> R.drawable.ic_favorite_selected
            else -> R.drawable.ic_favorite_unselected
        }
    setImageDrawable(context.getDrawable(icon))
}