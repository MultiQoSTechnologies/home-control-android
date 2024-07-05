package com.app.homecontrol.screens.fan

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
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
fun FanScreen(
    modifier: Modifier = Modifier,
    viewModel: RoomViewModel = hiltViewModel(),
) {

    Column(modifier = modifier.fillMaxSize()) {

        Box(
            modifier = modifier
                .padding(24.dp)
                .fillMaxSize()
                .weight(0.5f)
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

        Divider(
            modifier = Modifier.height(2.dp).fillMaxWidth().background(color = MaterialTheme.colorScheme.onBackground)
        )

        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize()
                .weight(0.5f)
        ) {

            val colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)

            val infiniteTransition = rememberInfiniteTransition(label = "InfiniteTransition")
            val initialValue = animateFloatAsState(
                targetValue = 0f,
                animationSpec = tween(2000)
            )

            if (viewModel.fanSpeed.value != 0 && viewModel.isFanOn.value) {

                val angle2 = if (viewModel.fanSpeed.value == 2000) {
                    infiniteTransition.animateFloat(
                        initialValue = initialValue.value,
                        targetValue = 360F,
                        animationSpec = infiniteRepeatable(
                            animation = tween(2000, easing = LinearEasing)
                        ), label = "InfiniteTransition"
                    )
                } else if (viewModel.fanSpeed.value == 1500) {
                    infiniteTransition.animateFloat(
                        initialValue = initialValue.value,
                        targetValue = 360F,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1500, easing = LinearEasing)
                        ), label = "InfiniteTransition"
                    )
                } else if (viewModel.fanSpeed.value == 1000) {
                    infiniteTransition.animateFloat(
                        initialValue = initialValue.value,
                        targetValue = 360F,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1000, easing = LinearEasing)
                        ), label = "InfiniteTransition"
                    )
                } else if (viewModel.fanSpeed.value == 500) {
                    infiniteTransition.animateFloat(
                        initialValue = initialValue.value,
                        targetValue = 360F,
                        animationSpec = infiniteRepeatable(
                            animation = tween(500, easing = LinearEasing)
                        ), label = "InfiniteTransition"
                    )
                } else {
                    infiniteTransition.animateFloat(
                        initialValue = initialValue.value,
                        targetValue = 360F,
                        animationSpec = infiniteRepeatable(
                            animation = tween(200, easing = LinearEasing)
                        ), label = "InfiniteTransition"
                    )
                }

                val speed = viewModel.fanSpeed.value

                /*val angle = infiniteTransition.animateFloat(
                    initialValue = 0F,
                    targetValue = 360F,
                    animationSpec = infiniteRepeatable(
                        animation = tween(speed, easing = LinearEasing)
                    ), label = "InfiniteTransition"
                )*/

                Image(
                    painter = painterResource(id = R.drawable.fan9),
                    contentDescription = "bulb",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(20.dp)
                        .graphicsLayer {
                            rotationZ = angle2.value
                        },
                    colorFilter = colorFilter
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.fan9),
                    contentDescription = "bulb",
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                        .padding(20.dp),
                    colorFilter = colorFilter
                )
            }

        }

    }

}
