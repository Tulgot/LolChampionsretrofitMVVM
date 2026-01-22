package com.tulgot.lol.presentation.championlistscreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tulgot.lol.domain.LolChampionsRepository
import com.tulgot.lol.domain.network.UiStates
import com.tulgot.lol.domain.network.internetconnectionobserver.domain.ConnectivityObserver
import com.tulgot.lol.presentation.testdata.ChampionDataTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class ChampionListViewModelTest {

    @RelaxedMockK private lateinit var lolChampionsRepository: LolChampionsRepository
    @RelaxedMockK private lateinit var connectivityObserver: ConnectivityObserver

    private var dispatcher = StandardTestDispatcher()
    private lateinit var championListViewModel: ChampionListViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockKAnnotations.init(this)
        championListViewModel =
            ChampionListViewModel(lolChampionsRepository, connectivityObserver, dispatcher)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun when_lolChampionRepository_return_a_champion_list_on_the_flow() = runTest {

        //Given
        val response = ChampionDataTest.getMockData()
        coEvery { lolChampionsRepository.getAllChampions() } returns flowOf(response)

        //When
        championListViewModel.championListEvents(
            ChampionListScreenEvents.LoadChampionList
        )
        dispatcher.scheduler.advanceUntilIdle()

        //Then
        val championList = championListViewModel.championListState.value.championList
        val state = championListViewModel.championListState.value.state
        assertEquals(response, championList)
        assertEquals(UiStates.SUCCESS, state)
    }



}