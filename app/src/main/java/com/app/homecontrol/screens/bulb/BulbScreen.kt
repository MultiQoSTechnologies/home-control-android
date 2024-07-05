package com.app.homecontrol.screens.bulb

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.homecontrol.R
import com.app.homecontrol.screens.room.RoomViewModel
import com.app.homecontrol.ui.theme.IntensityColor1
import com.app.homecontrol.ui.theme.IntensityColor2
import com.app.homecontrol.ui.theme.IntensityColor3
import com.app.homecontrol.ui.theme.IntensityColor4

@Preview
@Composable
fun BulbScreen(
    modifier: Modifier = Modifier,
    viewModel: RoomViewModel = hiltViewModel()
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        val id = if (viewModel.isBulbOn.value) R.drawable.ic_bulb_filled else R.drawable.ic_bulb

        var colorFilter: ColorFilter? = null

        val backgroundColor by animateColorAsState(
            targetValue = when (viewModel.bulbIntensity.value) {
                1 -> IntensityColor1
                2 -> IntensityColor2
                3 -> IntensityColor3
                else -> IntensityColor4
            },
            label = "background color",
            animationSpec = tween(durationMillis = 1000)
        )

        if (viewModel.isBulbOn.value) {
            colorFilter = ColorFilter.tint(color = backgroundColor)
        }


        Image(
            painter = painterResource(id = id),
            contentDescription = "bulb",
            modifier = Modifier
                .matchParentSize()
                .padding(20.dp),
            colorFilter = colorFilter
        )
    }
}