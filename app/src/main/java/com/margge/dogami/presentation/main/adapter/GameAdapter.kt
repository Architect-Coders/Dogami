package com.margge.dogami.presentation.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.margge.dogami.R
import com.margge.dogami.databinding.GameItemBinding
import com.margge.dogami.presentation.utils.basicDiffUtil
import com.margge.dogami.presentation.utils.bindingInflate
import com.margge.domain.Game

class GameAdapter(private val listener: (Game) -> Unit) :
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    var games: List<Game> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.id == new.id }
    )

    override fun getItemCount(): Int = games.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder =
        GameViewHolder(parent.bindingInflate(R.layout.game_item, false))

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = games[position]
        holder.databinding.game = game
        holder.itemView.setOnClickListener { listener(game) }
    }

    class GameViewHolder(val databinding: GameItemBinding) :
        RecyclerView.ViewHolder(databinding.root)
}