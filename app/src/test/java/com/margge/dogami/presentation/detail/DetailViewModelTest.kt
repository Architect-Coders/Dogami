package com.margge.dogami.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.margge.dogami.presentation.main.getMockedGame
import com.margge.usecases.GetGameByIdUseCase
import com.margge.usecases.UpdateGameUseCase
import com.nhaarman.mockitokotlin2.refEq
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
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    @get:Rule
    val rule = InstantTaskExecutorRule()
    @Mock
    lateinit var getGameByIdUseCase: GetGameByIdUseCase
    @Mock
    lateinit var updateGameUseCase: UpdateGameUseCase
    @Mock
    lateinit var observer: Observer<DetailViewModel.UiModel>
    private val gameId = 15

    @Before
    fun setUp() {
        viewModel =
            DetailViewModel(gameId, getGameByIdUseCase, updateGameUseCase, Dispatchers.Unconfined)
    }

    @Test
    fun `when liveData starts being observed then should call getGameByIdUseCase`() {
        runBlocking {
            //When
            val game = getMockedGame()
            whenever(getGameByIdUseCase.invoke(gameId)).thenReturn(game)
            viewModel.model.observeForever(observer)

            //Then
            verify(observer).onChanged(refEq(DetailViewModel.UiModel(game)))
        }
    }

    @Test
    fun `when onFavoriteGameClicked then should call updateGameUseCase`() {
        runBlocking {
            //When
            val game = getMockedGame()
            whenever(getGameByIdUseCase.invoke(gameId)).thenReturn(game)
            whenever(updateGameUseCase.invoke(game)).thenReturn(game.copy(favorite = !game.favorite))

            viewModel.model.observeForever(observer)
            viewModel.onFavoriteGameClicked()

            //Then
            verify(observer).onChanged(refEq(DetailViewModel.UiModel(game)))
        }
    }
}