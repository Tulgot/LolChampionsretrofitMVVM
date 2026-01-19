package com.tulgot.lol.presentation.championlistscreen

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.tulgot.lol.domain.LolChampionsRepository
import com.tulgot.lol.domain.model.Champion
import com.tulgot.lol.domain.model.ChampionResponse
import com.tulgot.lol.domain.model.Image
import com.tulgot.lol.domain.model.Passive
import com.tulgot.lol.domain.network.UiStates
import com.tulgot.lol.domain.network.internetconnectionobserver.domain.ConnectivityObserver
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
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

    @RelaxedMockK
    private lateinit var lolChampionsRepository: LolChampionsRepository

    @RelaxedMockK
    private lateinit var connectivityObserver: ConnectivityObserver

    private var dispatcher = StandardTestDispatcher()


    private lateinit var championListViewModel: ChampionListViewModel


    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun onBefore(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        MockKAnnotations.init(this)
        championListViewModel = ChampionListViewModel(lolChampionsRepository, connectivityObserver, dispatcher)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun when_lolchampionrepository_return_a_champion_list_on_the_flow() = runTest {

        //Given
        val response =
            ChampionResponse(
                type = "champion",
                format = "standAloneComplex",
                version = "15.7.1",
                data = listOf(
                    Champion(
                        blurb = "Once honored defenders of Shurima against the Void, Aatrox and his brethren would eventually become an even greater threat to Runeterra, and were defeated only by cunning mortal sorcery. But after centuries of imprisonment, Aatrox was the first to find...",
                        id = "Aatrox",
                        image = Image(
                            full = "Aatrox.png",
                            group = "champion"
                        ),
                        key = "266",
                        lore = "",
                        name = "Aatrox",
                        passive = Passive(
                            description = "",
                            image = Image(
                                full = "",
                                group = ""
                            ),
                            name = ""
                        ),
                        spells = emptyList(),
                        tags = listOf(
                            "Figther"
                        ),
                        title = "The Darkin Blade"
                    ),
                    Champion(
                        blurb = "Innately connected to the magic of the spirit realm, Ahri is a fox-like vastaya who can manipulate her prey's emotions and consume their essence—receiving flashes of their memory and insight from each soul she consumes. Once a powerful yet wayward...",
                        id = "Ahri",
                        image = Image(
                            full = "Ahri.png",
                            group = "champion"
                        ),
                        key = "103",
                        lore = "",
                        name = "Ahri",
                        passive = Passive(
                            description = "",
                            image = Image(
                                full = "",
                                group = ""
                            ),
                            name = ""
                        ),
                        spells = emptyList(),
                        tags = listOf(
                            "Mage",
                            "Assassin"
                        ),
                        title = "The Nine-Tailed Fox"
                    )
                )
            )
        coEvery { lolChampionsRepository.getAllChampions() } returns flowOf(response)

        //When
        championListViewModel.loadChampionList()
        dispatcher.scheduler.advanceUntilIdle()

        //Then
        val championList = championListViewModel.championListState.value.championList
        val state = championListViewModel.championListState.value.state
        assertEquals(response, championList)
        assertEquals(UiStates.SUCCESS, state)
    }
    

}