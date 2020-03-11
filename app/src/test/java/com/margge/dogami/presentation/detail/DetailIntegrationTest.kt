package com.margge.dogami.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.margge.dogami.DaggerTestDogamiComponent
import com.margge.dogami.FakeLocalDataSource
import com.margge.dogami.TestDogamiComponent
import com.margge.dogami.fakeGamesList
import com.margge.dogami.presentation.getFakeGame
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.refEq
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.assertTrue

@RunWith(MockitoJUnitRunner::class)
class DetailIntegrationTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()// LiveData haga todo en el hilo principal

    @Mock
    lateinit var observer: Observer<DetailViewModel.UiModel>
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var localDataSource: FakeLocalDataSource
    private val component: TestDogamiComponent = DaggerTestDogamiComponent.factory().create()
    private val gameId = 3

    @Before
    fun setUp() {
        detailViewModel = component.plus(DetailActivityModule(gameId)).detailViewModel
        localDataSource = component.localDataSource as FakeLocalDataSource
        localDataSource.gamesList = fakeGamesList
    }

    @Test
    fun `when viewModel is observed then should find a game`() {
        //When
        detailViewModel.model.observeForever(observer)

        //Then
        verify(observer).onChanged(refEq(DetailViewModel.UiModel(getFakeGame().copy(id = gameId))))
    }

    @Test
    fun `when onFavoriteGameClicked then should update the localDataSource`() {
        runBlocking {
            //When
            detailViewModel.model.observeForever(observer)
            detailViewModel.onFavoriteGameClicked()

            //Then
            assertTrue(localDataSource.getBoardGameById(gameId).favorite)
        }
    }
}