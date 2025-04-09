package com.tulgot.lol.domain.network

import com.tulgot.lol.domain.EMPTY_STATE

sealed class UiStates(val messege: String) {
    data object SUCCESS: UiStates(messege = "SUCCESS")
    data object FAILURE: UiStates(messege = "FAILURE")
    data object LOADING: UiStates(messege = "LODING")
    data object NONE: UiStates(messege = EMPTY_STATE)
}