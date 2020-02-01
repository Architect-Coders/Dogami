package com.margge.dogami.data.server

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface DogamiApi {
    @GET("games")
    fun getListGamesAsync(
        @Query("language") query: String? = null,
        @Query("page") page: Int? = null,
        @Query("location") location: String
    ): Deferred<List<DogamiGameResult>>
}