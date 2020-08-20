package com.margge.dogami

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DogamiApp : Application()

//{
//    lateinit var component: DogamiComponent
//        private set
//
//    override fun onCreate() {
//        super.onCreate()
//        component = initComponent()
//    }
//
//    open fun initComponent() = DaggerDogamiComponent.factory().create(this)
//}