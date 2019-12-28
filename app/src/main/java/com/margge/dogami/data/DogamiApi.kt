package com.margge.dogami.data

import com.margge.dogami.Game
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface DogamiApi {
    @GET("games")
    fun getListGamesAsync(
        @Query("language") query: String? = null,
        @Query("page") page: Int? = null
    ): Deferred<List<Game>>
}