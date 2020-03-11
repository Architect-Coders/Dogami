package com.margge.dogami.presentation

import android.app.Application
import android.content.Intent
import android.os.SystemClock
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.jakewharton.espresso.OkHttp3IdlingResource
import com.margge.dogami.DaggerUiTestDogamiComponent
import com.margge.dogami.R
import com.margge.dogami.fakeGamesResponse
import com.margge.dogami.presentation.main.MainActivity
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.time.ExperimentalTime

@RunWith(AndroidJUnit4::class)
class UITests {

    @ExperimentalTime
    @get:Rule
    val activityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, false, false)

    @get:Rule
    val grantPermissionRule: GrantPermissionRule =
        GrantPermissionRule.grant("android.permission.ACCESS_COARSE_LOCATION")

    private lateinit var mockWebServer: MockWebServer

    @ExperimentalTime
    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        val app = instrumentation.targetContext.applicationContext as Application
        val component = DaggerUiTestDogamiComponent.factory().create(app)

        mockWebServer = component.mockWebServer

        val resource = OkHttp3IdlingResource.create("OkHttp", component.networkHelper.okHttpClient)
        IdlingRegistry.getInstance().register(resource)

        val intent = Intent(instrumentation.targetContext, MainActivity::class.java)
        activityTestRule.launchActivity(intent)

        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(fakeGamesResponse))
    }

    @ExperimentalTime
    @Test
    fun viewLoadedDisplaysGamesRecyclerView() {
        onView(withId(R.id.gamesRecycler)).check(matches(isDisplayed()))
    }

    @ExperimentalTime
    @Test
    fun clickAGameNavigatesToDetail() {
        SystemClock.sleep(2000)

        onView(withId(R.id.gamesRecycler)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                ViewActions.click()
            )
        )

        onView(withId(R.id.gameDetailToolbar))
            .check(matches(ViewMatchers.hasDescendant(ViewMatchers.withText("Eldritch horror"))))
    }

    @After
    fun tearDown() {
        mockWebServer.close()
    }
}