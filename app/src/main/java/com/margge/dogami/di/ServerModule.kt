package com.margge.dogami.di

import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ServerModule {

    private val url = "https://api.steinhq.com/v1/storages/5df2fb845a823204986f39aa/"

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider(): String = url
}