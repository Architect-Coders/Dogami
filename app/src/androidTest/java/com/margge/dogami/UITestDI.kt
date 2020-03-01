package com.margge.dogami

import android.app.Application
import com.margge.dogami.data.server.DogamiNetworkHelper
import com.margge.dogami.di.AppModule
import com.margge.dogami.di.DataModule
import com.margge.dogami.di.DogamiComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Named
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DataModule::class,
        UiTestServerModule::class
    ]
)
interface UiTestDogamiComponent : DogamiComponent {

    val networkHelper: DogamiNetworkHelper
    val mockWebServer: MockWebServer

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): UiTestDogamiComponent
    }
}

@Module
class UiTestServerModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider(): String = "http://127.0.0.1:8080"

    @Provides
    @Singleton
    fun mockWebServerProvider(): MockWebServer {
        var mockWebServer: MockWebServer? = null
        val thread = Thread(Runnable {
            mockWebServer = MockWebServer()
            mockWebServer?.start(8080)
        })
        thread.start()
        thread.join()
        return mockWebServer ?: throw NullPointerException()
    }

    @Provides
    @Singleton
    fun networkHelperProvider(@Named("baseUrl") baseUrl: String)
            : DogamiNetworkHelper = DogamiNetworkHelper(baseUrl)
}