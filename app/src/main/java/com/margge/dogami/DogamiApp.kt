package com.margge.dogami

import android.app.Application
import androidx.room.Room
import com.margge.dogami.data.database.DogamiDatabase

class DogamiApp : Application() {

    lateinit var db: DogamiDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            this,
            DogamiDatabase::class.java, "boardGame-db"
        ).build()
    }
}