package com.margge.dogami.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.margge.dogami.DaggerTestDogamiComponent
import com.margge.dogami.FakeLocalDataSource
import com.margge.dogami.TestDogamiComponent
import com.margge.dogami.fakeGamesList
import com.margge.dogami.presentation.getFakeGame
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.refEq
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainIntegrationTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()// LiveData haga todo en el hilo principal

    @Mock
    lateinit var observer: Observer<MainViewModel.UiModel>
    private lateinit var mainViewModel: MainViewModel
    private val component: TestDogamiComponent = DaggerTestDogamiComponent.factory().create()

    @Before
    fun setUp() {
        mainViewModel = component.plus(MainActivityModule()).mainViewModel
    }

    @Test
    fun `when localDataSource is empty then data is loaded from server`() {
        //When
        mainViewModel.model.observeForever(observer)
        mainViewModel.onCoarsePermissionRequested()

        //Then
        verify(observer).onChanged(refEq(MainViewModel.UiModel.Content(fakeGamesList)))
    }

    @Test
    fun `when localDataSource has data then data is returned`() {
        //When
        val games = listOf(getFakeGame().copy(67), getFakeGame().copy(19))
        val localDataSource = component.localDataSource as FakeLocalDataSource
        localDataSource.gamesList = games
        mainViewModel.model.observeForever(observer)

        mainViewModel.onCoarsePermissionRequested()

        //Then
        verify(observer).onChanged(MainViewModel.UiModel.Content(games))
    }
}