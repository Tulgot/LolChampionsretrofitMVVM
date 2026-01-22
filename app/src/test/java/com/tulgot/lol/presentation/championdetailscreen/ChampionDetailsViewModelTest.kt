package com.tulgot.lol.presentation.championdetailscreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.tulgot.lol.domain.LolChampionsRepository
import com.tulgot.lol.domain.network.UiStates
import com.tulgot.lol.domain.network.internetconnectionobserver.domain.ConnectivityObserver
import com.tulgot.lol.domain.room.RoomManager
import com.tulgot.lol.modules.firestore.domain.FireStoreManager
import com.tulgot.lol.modules.login.domain.AuthProvider
import com.tulgot.lol.presentation.testdata.ChampionDataTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
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
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
class ChampionDetailsViewModelTest {

    @RelaxedMockK
    private lateinit var lolChampionsRepository: LolChampionsRepository
    @RelaxedMockK
    private lateinit var roomManager: RoomManager
    @RelaxedMockK
    private lateinit var fireStoreManager: FireStoreManager
    @RelaxedMockK
    private lateinit var connectivityObserver: ConnectivityObserver

    private var authProvider = mockk<AuthProvider>(relaxed = true)
    private var savedStateHandle = SavedStateHandle()
    private var dispatcher = StandardTestDispatcher()

    private lateinit var championDetailsViewModel: ChampionDetailsViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockKAnnotations.init(this)
        savedStateHandle = SavedStateHandle(
            mapOf("name" to "")
        )
        championDetailsViewModel = ChampionDetailsViewModel(
            lolChampionsRepository,
            roomManager,
            fireStoreManager,
            connectivityObserver,
            authProvider,
            savedStateHandle,
            dispatcher
        )
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun when_lolChampionRepository_returns_championDetails_on_the_flow() = runTest {
        //Given
        val response = ChampionDataTest.getMockData()
        coEvery { lolChampionsRepository.getChampionDetails("") } returns flowOf(response)

        //When
        championDetailsViewModel.loadChampionDetails("")
        dispatcher.scheduler.advanceUntilIdle()

        //Then
        val championDetail = championDetailsViewModel.championDetailsState.value.championDetails
        val championDetailState = championDetailsViewModel.championDetailsState.value.state
        assertEquals(response, championDetail)
        assertEquals(UiStates.SUCCESS, championDetailState)
    }

}