package com.app.homecontrol.screens.room

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.homecontrol.R
import com.app.homecontrol.core.components.Ac
import com.app.homecontrol.core.components.Fan
import com.app.homecontrol.core.components.Light
import com.app.homecontrol.core.components.Refrigerator
import com.app.homecontrol.core.components.SearchBar
import com.app.homecontrol.core.components.SpacerVertical
import com.app.homecontrol.core.components.TV
import com.app.homecontrol.core.utils.FirestoreKeys
import com.app.homecontrol.core.utils.toInt
import com.app.homecontrol.navigation.nav_args.RoomArgsModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    roomArgsModel: RoomArgsModel,
    roomViewModel: RoomViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        roomViewModel.getDevices(roomArgsModel.room?.id ?: -1)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = roomArgsModel.room?.roomName ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    FilledIconButton(
                        onClick = { navHostController.navigateUp() },
                        modifier = Modifier.padding(8.dp),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.onSurface
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_button),
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            )
        }
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            item {
                SearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    stringResource(id = R.string.search_device)
                )
            }

            items(roomViewModel.devices) { item ->

                when (item.deviceType) {
                    FirestoreKeys.LIGHT -> Light(device = item,
                        statusUpdated = { isOn ->
                            roomViewModel.updateDeviceStatus(item, isOn.toInt())
                        },
                        intensityValueUpdated = {intensity->
                            roomViewModel.updateDeviceValue(item, intensity)
                        })

                    FirestoreKeys.FAN -> Fan(device = item,
                        fanValueUpdate = { value ->
                            roomViewModel.updateDeviceValue(item, value)
                        },
                        statusUpdate = { isOn ->
                            roomViewModel.updateDeviceStatus(item, isOn.toInt())
                        })

                    FirestoreKeys.AC -> Ac(device = item,
                        acTemperatureUpdate = { temperature ->
                            roomViewModel.updateDeviceValue(item, temperature)
                        },
                        statusUpdate = { isOn ->
                            roomViewModel.updateDeviceStatus(item, isOn.toInt())
                        })

                    FirestoreKeys.TV -> TV(device = item) { isOn ->
                        roomViewModel.updateDeviceStatus(item, isOn.toInt())
                    }

                    FirestoreKeys.REFRIGERATOR -> Refrigerator(device = item) { isOn ->
                        roomViewModel.updateDeviceStatus(item, isOn.toInt())
                    }
                }

            }

            item {
                SpacerVertical(height = 8.dp)
            }
        }
    }
}