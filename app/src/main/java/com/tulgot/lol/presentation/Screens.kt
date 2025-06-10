package com.tulgot.lol.presentation

import kotlinx.serialization.Serializable

//Login
@Serializable object SignInGraph

//Login Graph
@Serializable object LoginRoute
@Serializable object RegistrationRoute

//App
@Serializable object HomeGraph

//APP Graph
@Serializable object ChampionListRoute
@Serializable data class ChampionDetailsRoute(val name: String)
@Serializable object BookMarksRoute
@Serializable data class BookMarksDetailRoute(val id: String)
@Serializable object SettingsRoute
@Serializable object ProfileRoute
@Serializable object GoogleMapRoute











