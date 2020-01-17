package com.margge.dogami.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Game::class], version = 1)
abstract class DogamiDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
}