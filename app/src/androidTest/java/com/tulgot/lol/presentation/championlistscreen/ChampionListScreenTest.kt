package com.tulgot.lol.presentation.championlistscreen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.tulgot.lol.domain.model.ChampionResponse
import com.tulgot.lol.domain.network.UiStates
import com.tulgot.lol.ui.theme.LOLTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ChampionListScreenTest {

//    @get:Rule
//    val composeRule = createComposeRule()

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>().apply {
        mainClock.autoAdvance = true
    }

        @Test
        fun initialState_isRendered() {
            composeRule.setContent {
                    ChampionListScreen(
                        state = ChampionListState(
                            state = UiStates.NONE,
                            championList = ChampionResponse(emptyList())
                        ),
                        isConnected = true,
                        navigateToDetail = {}
                    )
            }

            composeRule.mainClock.advanceTimeBy(5_000)
            composeRule.waitForIdle()

            composeRule.onNodeWithTag(
                "None Indicator"
            ).assertExists()
        }

}