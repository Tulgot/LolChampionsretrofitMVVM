package com.tulgot.lol.presentation.settingscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tulgot.lol.domain.SETTINGS_NAME
import com.tulgot.lol.domain.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    val dataStoreState = mutableStateOf(false)

    init {
        getSetting()
    }

    fun change(value: Boolean) {
        putSettings(value)
        getSetting()
    }


    private fun putSettings(value: Boolean) {
        viewModelScope.launch {
            dataStoreManager.putSettings(SETTINGS_NAME, value)
            dataStoreState.value = value
        }
    }

    private fun getSetting() {
        viewModelScope.launch {
            dataStoreManager.getSettings(SETTINGS_NAME)?.let { value ->
                dataStoreState.value = value
            }
        }
    }

}

