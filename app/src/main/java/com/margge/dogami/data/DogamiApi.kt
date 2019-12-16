package com.margge.dogami.data

import com.margge.dogami.Game
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface DogamiApi {
    @GET("games")
    fun getListGames(
        @Query("language") query: String? = null,
        @Query("page") page: Int? = null
    ): Single<List<Game>>
}