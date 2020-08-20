package com.margge.dogami.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ServerModule {

    private val url = "https://api.steinhq.com/v1/storages/5df2fb845a823204986f39aa/"

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider(): String = url
}