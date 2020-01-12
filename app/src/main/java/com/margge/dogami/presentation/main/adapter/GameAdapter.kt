package com.margge.dogami.presentation.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.margge.dogami.Game
import com.margge.dogami.R
import com.margge.dogami.presentation.utils.basicDiffUtil
import com.margge.dogami.presentation.utils.inflate
import com.margge.dogami.presentation.utils.loadUrl
import kotlinx.android.synthetic.main.game_item.view.*

class GameAdapter(private val listener: (Game) -> Unit) :
    RecyclerView.Adapter<GameAdapter.MovieViewHolder>() {

     var games: List<Game> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun getItemCount(): Int = games.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = parent.inflate(R.layout.game_item, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = games[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(game: Game) {
            itemView.gamePosterImageView.loadUrl(game.imageUrl)
            itemView.gameDetailName.text = game.name
        }
    }
}