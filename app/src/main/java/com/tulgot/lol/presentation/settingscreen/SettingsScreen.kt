package com.tulgot.lol.presentation.settingscreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.tulgot.lol.ui.theme.Typography

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navigateToChampionList: () -> Unit,
    navigateToBookMarks: () -> Unit,
    dataStoreViewModel: DataStoreViewModel = hiltViewModel(),
) {

    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Settings")
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                imageVector = Icons.Rounded.MoreVert,
                                contentDescription = "More options"
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Settings") },
                                onClick = { navigateToBookMarks() }
                            )
                            DropdownMenuItem(
                                text = { Text("Champion List") },
                                onClick = { navigateToChampionList() }
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(top = innerPadding.calculateTopPadding())
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .toggleable(
                        value = dataStoreViewModel.dataStoreState.value,
                        onValueChange = {
                            dataStoreViewModel.changeThemeSwitch(it)
                        },
                        role = Role.Switch,
                        enabled = if (dataStoreViewModel.dataStoreCheckBox.value) false else true
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = "Dark Mode",
                    textAlign = TextAlign.Start,
                )


                Switch(
                    checked = dataStoreViewModel.dataStoreState.value,
                    onCheckedChange = null,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(12.dp)
                    .toggleable(
                        value = dataStoreViewModel.dataStoreCheckBox.value,
                        onValueChange = {
                            if (it) {
                                dataStoreViewModel.changeThemeSwitch(false)
                                dataStoreViewModel.changeCheckBox(it)
                            } else {
                                dataStoreViewModel.changeCheckBox(it)
                            }
                            //themeSwithcerTheme(content = content)
                        },
                        role = Role.Switch
                    )
            ) {
                Text("Use Device Theme", textAlign = TextAlign.Start)
                Checkbox(
                    checked = dataStoreViewModel.dataStoreCheckBox.value,
                    onCheckedChange = null,
                    modifier = Modifier.align(Alignment.CenterEnd)
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun themeSwithcerTheme(
    content: @Composable () -> Unit
) {

    val viewModel = hiltViewModel<DataStoreViewModel>()
    val colorSchemeDataStore =
        if (viewModel.dataStoreState.value) dynamicDarkColorScheme(LocalContext.current) else dynamicLightColorScheme(
            LocalContext.current
        )
    val colorSchemeSystem =
        if (isSystemInDarkTheme()) dynamicDarkColorScheme(LocalContext.current) else dynamicLightColorScheme(
            LocalContext.current
        )

    when (viewModel.dataStoreCheckBox.value) {
        true -> {
            MaterialTheme(
                colorScheme = colorSchemeSystem,
                typography = Typography,
                content = content
            )
        }

        false -> {
            MaterialTheme(
                colorScheme = colorSchemeDataStore,
                typography = Typography,
                content = content
            )
        }
    }

}
