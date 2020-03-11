package com.margge.dogami

import android.app.Application
import com.margge.dogami.di.DaggerDogamiComponent
import com.margge.dogami.di.DogamiComponent

open class DogamiApp : Application() {

    lateinit var component: DogamiComponent
        private set

    override fun onCreate() {
        super.onCreate()
        component = initComponent()
    }

    open fun initComponent() = DaggerDogamiComponent.factory().create(this)
}