package com.margge.dogami.presentation.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.margge.dogami.R
import com.margge.dogami.data.database.Game
import com.margge.dogami.presentation.utils.basicDiffUtil
import com.margge.dogami.presentation.utils.inflate
import com.margge.dogami.presentation.utils.loadUrl
import kotlinx.android.synthetic.main.game_item.view.*

class GameAdapter(private val listener: (Game) -> Unit) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    var games: List<Game> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun getItemCount(): Int = games.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = parent.inflate(R.layout.game_item, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game)
        holder.itemView.setOnClickListener { listener(game) }
    }

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(game: Game) {
            if (game.imageUrl == null) {
                itemView.gamePosterImageView.loadUrl("https://i.imgur.com/FVOHmfg.png")
            } else {
                itemView.gamePosterImageView.loadUrl(game.imageUrl)
            }

            itemView.gameDetailName.text = game.name
        }
    }
}