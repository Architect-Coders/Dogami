package com.margge.dogami

import android.app.Application
import com.margge.dogami.di.DaggerDogamiComponent
import com.margge.dogami.di.DogamiComponent

class DogamiApp : Application() {

    lateinit var component: DogamiComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerDogamiComponent
            .factory()
            .create(this)
    }
}