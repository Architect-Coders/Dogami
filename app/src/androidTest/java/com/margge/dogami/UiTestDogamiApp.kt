package com.margge.dogami

class UiTestDogamiApp : DogamiApp() {

    override fun initComponent() = DaggerUiTestDogamiComponent.factory().create(this)
}