package com.tulgot.lol.presentation.settingscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tulgot.lol.domain.SETTINGS_NAME
import com.tulgot.lol.domain.DEVICE_CHECKBOX
import com.tulgot.lol.domain.datastore.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ViewModel() {

    init {
        getSetting()
        getDeviceCheckBox()
    }

    val dataStoreState = mutableStateOf(false)
    val dataStoreCheckBox = mutableStateOf(true)

    fun changeThemeSwitch(value: Boolean) {
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

    fun changeCheckBox(value: Boolean) {
        putDeviceCheckBox(value)
        getDeviceCheckBox()
    }

    private fun putDeviceCheckBox(value: Boolean){
        viewModelScope.launch {
            dataStoreManager.putDeviceCheckBox(DEVICE_CHECKBOX, value)
            dataStoreCheckBox.value = value
        }
    }

    private fun getDeviceCheckBox(){
        viewModelScope.launch {
            dataStoreManager.getDeviceCheckBox(DEVICE_CHECKBOX)?.let {
                dataStoreCheckBox.value = it
            }
        }
    }
}

