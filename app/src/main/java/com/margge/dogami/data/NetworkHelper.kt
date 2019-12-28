package com.margge.dogami.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkHelper {

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.steinhq.com/v1/storages/5df2fb845a823204986f39aa/")
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun dogamiApi(): DogamiApi {
        return retrofit().create(DogamiApi::class.java)
    }
}