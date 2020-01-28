package com.margge.dogami.di

import android.app.Application
import androidx.room.Room
import com.margge.data.source.LocalDataSource
import com.margge.data.source.RemoteDataSource
import com.margge.dogami.data.database.GameDatabase
import com.margge.dogami.data.database.RoomDataSource
import com.margge.dogami.data.server.DogamiDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun databaseProvider(app: Application) =
        Room.databaseBuilder(
            app,
            GameDatabase::class.java, "boardGame-db"
        ).build()

    @Provides
    fun localDataSourceProvider(db: GameDatabase): LocalDataSource = RoomDataSource(db)

    @Provides
    fun remoteDataSourceProvider(): RemoteDataSource = DogamiDataSource()
}