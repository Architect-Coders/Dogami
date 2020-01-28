package com.margge.dogami.di

import com.margge.dogami.presentation.detail.DetailActivityComponent
import com.margge.dogami.presentation.detail.DetailActivityModule
import com.margge.dogami.presentation.main.MainActivityComponent
import com.margge.dogami.presentation.main.MainActivityModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class])
interface DogamiComponent {

    fun plus(module: MainActivityModule): MainActivityComponent
    fun plus(module: DetailActivityModule): DetailActivityComponent
}