package com.margge.dogami.presentation.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.margge.dogami.presentation.getFakeGame
import com.margge.usecases.GetGamesUseCase
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel
    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Mock
    lateinit var getGamesUseCase: GetGamesUseCase
    @Mock
    lateinit var observer: Observer<MainViewModel.UiModel>

    @Before
    fun setUp() {
        viewModel = MainViewModel(getGamesUseCase, Dispatchers.Unconfined)
    }

    @Test
    fun `when liveData starts being observed then locationPermission should be requested`() {
        //When
        viewModel.model.observeForever(observer)

        //Then
        verify(observer).onChanged(MainViewModel.UiModel.RequestLocationPermission)
    }

    @Test
    fun `when onGameClicked then should call navigation event with the specific game`() {
        //When
        val game = getFakeGame()
        viewModel.model.observeForever(observer)
        viewModel.onGameClicked(game)

        //Then
        verify(observer).onChanged(MainViewModel.UiModel.Navigation(game))
    }

    @Test
    fun `when locationPermission was requested then should launch loading`() {
        //When
        runBlocking {
            whenever(getGamesUseCase.invoke()).thenReturn(listOf(getFakeGame()))
            viewModel.model.observeForever(observer)
            viewModel.onCoarsePermissionRequested()
        }

        //Then
        verify(observer).onChanged(MainViewModel.UiModel.Loading)
    }

    @Test
    fun `when locationPermission was requested then should updated content`() {
        //When
        val gamesList = listOf(getFakeGame())
        runBlocking {
            whenever(getGamesUseCase.invoke()).thenReturn(gamesList)
            viewModel.model.observeForever(observer)
            viewModel.onCoarsePermissionRequested()
        }

        //Then
        verify(observer).onChanged(MainViewModel.UiModel.Content(gamesList))
    }
}