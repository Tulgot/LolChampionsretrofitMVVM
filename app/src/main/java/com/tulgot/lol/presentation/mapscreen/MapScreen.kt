package com.tulgot.lol.presentation.mapscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen() {

    var expanded by remember { mutableStateOf(false) }

    val locations = listOf(
        locations("Singapore", 1.35, 103.87),
        locations("Tijuana", 32.457204, -117.033885),
        locations("Nueva York", 40.6969824, -74.2913196),
        locations("Japon", 34.6667215, 131.8543236)
    )

    var location = LatLng(12.8682779, -86.3368105)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(location, 5f)
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        ) {
            Box {
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        expanded = true
                    }
                ) {
                    Text("Localizaciones")
                    Icon(Icons.Default.MoreVert, contentDescription = null)
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    repeat(locations.size) { index ->
                        DropdownMenuItem(
                            text = { Text(locations[index].Country) },
                            onClick = {
                                location =
                                    LatLng(locations[index].latitude, locations[index].longitude)
                                cameraPositionState.position =
                                    CameraPosition.fromLatLngZoom(location, 10f)
                            }
                        )
                    }
                }

            }
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                repeat(locations.size) { index ->
                    Marker(
                        state = MarkerState(
                            position = LatLng(
                                locations[index].latitude,
                                locations[index].longitude
                            )
                        ),
                        title = locations[index].Country

                    )
                }
            }
        }
    }
}

data class locations(
    val Country: String,
    val latitude: Double,
    val longitude: Double
)