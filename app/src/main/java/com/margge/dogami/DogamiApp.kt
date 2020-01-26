package com.margge.dogami

import android.app.Application
import androidx.room.Room
import com.margge.dogami.data.database.GameDatabase

class DogamiApp : Application() {

    lateinit var db: GameDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            GameDatabase::class.java, "boardGame-db"
        ).build()
    }
}