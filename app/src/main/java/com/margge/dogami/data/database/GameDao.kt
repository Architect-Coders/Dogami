package com.margge.dogami.data.database

import androidx.room.*

@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertGames(games: List<Game>)

    @Update
    fun updateGame(game: Game)

    @Query("SELECT * FROM Game")
    fun loadAllGames(): List<Game>

    @Query("SELECT * FROM Game WHERE id = :id")
    fun findById(id: Int): Game

    @Query("SELECT COUNT(id) FROM Game")
    fun gameCount(): Int
}