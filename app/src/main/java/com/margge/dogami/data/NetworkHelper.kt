package com.margge.dogami.data

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {

    fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.steinhq.com/v1/storages/5df2fb845a823204986f39aa/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun tmdbApi(): DogamiApi {
        return retrofit().create(DogamiApi::class.java)
    }
}